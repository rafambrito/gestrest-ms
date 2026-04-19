package br.com.gestrest.pedido.service.domain.model.ports.out;

import java.math.BigDecimal;

public interface PagamentoProcessadorPort {
    boolean processar(Long pedidoId, Long usuarioId, BigDecimal valorTotal);
}
