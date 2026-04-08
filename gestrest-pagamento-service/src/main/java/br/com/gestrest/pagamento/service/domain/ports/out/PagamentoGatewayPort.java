package br.com.gestrest.pagamento.service.domain.ports.out;

import java.math.BigDecimal;

public interface PagamentoGatewayPort {
    boolean cobrar(Long pedidoId, Long usuarioId, BigDecimal valor);
}
