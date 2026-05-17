package br.com.gestrest.pagamento.service.domain.model.ports.out;

import java.math.BigDecimal;

public interface PagamentoGatewayPort {
    PagamentoGatewayResponse iniciarPagamento(Long pagamentoId, Long pedidoId, Long clienteId, BigDecimal valor);
    String consultarStatus(String pagamentoIdExterno);
}
