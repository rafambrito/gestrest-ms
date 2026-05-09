package br.com.gestrest.pedido.service.adapters.out.integration.dto;

import java.math.BigDecimal;

public record PagamentoRequest(
        Long pedidoId,
        Long usuarioId,
        BigDecimal valor) {
}
