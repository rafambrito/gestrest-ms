package br.com.gestrest.pagamento.service.adapters.out.integration;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoGatewayPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PagamentoGatewayMockAdapter implements PagamentoGatewayPort {

    private final PoliticaResilienciaPagamento politica;

    @Override
    public boolean cobrar(Long pedidoId, Long usuarioId, BigDecimal valor) {
        int tentativas = 0;
        while (tentativas < politica.getMaxTentativas()) {
            tentativas++;
            if (valor.compareTo(BigDecimal.valueOf(1500)) <= 0) {
                return true;
            }
        }
        return false;
    }
}
