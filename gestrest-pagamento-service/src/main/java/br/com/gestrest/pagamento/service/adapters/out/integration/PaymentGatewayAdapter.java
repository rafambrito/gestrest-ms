package br.com.gestrest.pagamento.service.adapters.out.integration;

import java.math.BigDecimal;
import java.time.Duration;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.gestrest.pagamento.service.adapters.out.integration.dto.ProcpagRequisicaoRequest;
import br.com.gestrest.pagamento.service.adapters.out.integration.dto.ProcpagRequisicaoResponse;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoGatewayPort;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoGatewayResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Component
public class PaymentGatewayAdapter implements PagamentoGatewayPort {

    private final RestTemplate restTemplate;
    private final String procpagUrl;

    public PaymentGatewayAdapter(RestTemplateBuilder builder,
            @Value("${procpag.url}") String procpagUrl,
            @Value("${procpag.timeout:5s}") Duration timeout) {
        this.restTemplate = builder
            .requestFactory(() -> {
                var factory = new HttpComponentsClientHttpRequestFactory();
                factory.setConnectTimeout((int) timeout.toMillis());
                factory.setReadTimeout((int) timeout.toMillis());
                return factory;
            })
            .build();
        this.procpagUrl = procpagUrl;
    }

    @Override
    @Retry(name = "pagamentoService")
    @CircuitBreaker(name = "pagamentoService", fallbackMethod = "fallbackPagamento")
    public PagamentoGatewayResponse iniciarPagamento(Long pagamentoId, Long pedidoId, Long clienteId, BigDecimal valor) {
        try {
            return iniciarPagamentoAsync(pagamentoId, pedidoId, clienteId, valor).join();
        } catch (Exception ex) {
            if (AopUtils.isAopProxy(this)) {
                throw ex;
            }
            log.warn("Timeout ou erro ao iniciar pagamento (sync wrapper). pagamentoId={}, pedidoId={}, error={}", pagamentoId, pedidoId, ex.getMessage());
            var cause = ex.getCause() != null ? ex.getCause() : ex;
            return fallbackPagamento(pagamentoId, pedidoId, clienteId, valor, cause);
        }
    }

    public CompletableFuture<PagamentoGatewayResponse> iniciarPagamentoAsync(Long pagamentoId, Long pedidoId, Long clienteId, BigDecimal valor) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var requisicao = new ProcpagRequisicaoRequest(pagamentoId.toString(), clienteId.toString(), valor);

                var response = restTemplate.postForObject(
                    procpagUrl + "/requisicao",
                    requisicao,
                    ProcpagRequisicaoResponse.class
                );     

                if (response == null) {
                    log.warn("Resposta nula do procpag para pagamento: {}", pagamentoId);
                    return new PagamentoGatewayResponse(null, "falha");
                }

                var status = consultarStatusRequisicao(response.getId());
                log.info("Pagamento procpag para pagamento {}: status {}", pagamentoId, status);
                return new PagamentoGatewayResponse(response.getId(), status);
            } catch (RestClientException ex) {
                log.error("Erro na chamada ao procpag para pagamento: {}", pagamentoId, ex);
                throw ex;
            }
        });
 
    }

    @Override
    public String consultarStatus(String pagamentoIdExterno) {
        if (pagamentoIdExterno == null) {
            return "falha";
        }
        return consultarStatusRequisicao(pagamentoIdExterno);
    }

    private String consultarStatusRequisicao(String pagamentoIdExterno) {
        try {
            var response = restTemplate.getForObject(
                procpagUrl + "/requisicao/" + pagamentoIdExterno,
                ProcpagRequisicaoResponse.class
            );
            return response != null ? response.getStatus() : "falha";
        } catch (RestClientException ex) {
            log.error("Erro ao consultar status da requisição: {}", pagamentoIdExterno, ex);
            return "falha";
        }
    }

    public PagamentoGatewayResponse fallbackPagamento(Long pagamentoId, Long pedidoId, Long clienteId, BigDecimal valor, Throwable ex) {
        log.error("Fallback acionado para pagamento pendente. pagamentoId={}, pedidoId={}, usuarioId={}", pagamentoId, pedidoId, clienteId, ex);
        return new PagamentoGatewayResponse(null, "PENDENTE");
    }

    public CompletableFuture<PagamentoGatewayResponse> fallbackPagamentoAsync(Long pagamentoId, Long pedidoId, Long clienteId, BigDecimal valor, Throwable ex) {
        log.error("Fallback async acionado para pagamento pendente. pagamentoId={}, pedidoId={}, usuarioId={}", pagamentoId, pedidoId, clienteId, ex);
        return CompletableFuture.completedFuture(new PagamentoGatewayResponse(null, "PENDENTE"));
    }
}
