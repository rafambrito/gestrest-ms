package br.com.gestrest.pedido.service.adapters.out.integration;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import br.com.gestrest.pedido.service.adapters.out.integration.dto.PagamentoRequest;
import br.com.gestrest.pedido.service.adapters.out.integration.dto.PagamentoResponse;
import br.com.gestrest.pedido.service.domain.model.ports.out.PagamentoProcessadorPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PagamentoServiceAdapter implements PagamentoProcessadorPort {

    private final RestTemplate restTemplate;

    @Value("${gestrest.pagamento.service.url}")
    private String pagamentoServiceUrl;

    @Override
    @Retry(name = "pagamentoRetry")
    @CircuitBreaker(name = "pagamentoCircuitBreaker", fallbackMethod = "fallbackProcessar")
    public boolean processar(Long pedidoId, Long usuarioId, BigDecimal valorTotal) {
        try {
            PagamentoRequest request = new PagamentoRequest(pedidoId, usuarioId, valorTotal);
            PagamentoResponse response = restTemplate.postForObject(
                    pagamentoServiceUrl != null ? pagamentoServiceUrl : "",
                    request,
                    PagamentoResponse.class);

            if (response != null && response.aprovado() != null) {
                return response.aprovado();
            }
            return false;
        } catch (RestClientException ex) {
            return false;
        }
    }

    private boolean fallbackProcessar(Long pedidoId, Long usuarioId, BigDecimal valorTotal, Throwable throwable) {
        return false;
    }
}
