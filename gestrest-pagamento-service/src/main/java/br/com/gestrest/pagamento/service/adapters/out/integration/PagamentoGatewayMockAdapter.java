package br.com.gestrest.pagamento.service.adapters.out.integration;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoGatewayPort;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoGatewayResponse;
import lombok.RequiredArgsConstructor;

@Component(value = "pagamentoGatewayMockAdapter")
@RequiredArgsConstructor
public class PagamentoGatewayMockAdapter implements PagamentoGatewayPort {

    private final PoliticaResilienciaPagamento politica;

    @Override
    public PagamentoGatewayResponse iniciarPagamento(Long pagamentoId, Long clienteId, BigDecimal valor) {
        int tentativas = 0;
        while (tentativas < politica.getMaxTentativas()) {
            tentativas++;
            if (valor.compareTo(BigDecimal.valueOf(1500)) <= 0) {
                return new PagamentoGatewayResponse(String.valueOf(pagamentoId), "sucesso");
            }
        }
        return new PagamentoGatewayResponse(String.valueOf(pagamentoId), "falha");
    }

    @Override
    public String consultarStatus(String pagamentoIdExterno) {
        return "sucesso".equals(pagamentoIdExterno) ? "sucesso" : "falha";
    }
}
