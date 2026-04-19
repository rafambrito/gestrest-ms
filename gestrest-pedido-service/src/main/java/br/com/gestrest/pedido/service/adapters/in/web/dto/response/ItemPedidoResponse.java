package br.com.gestrest.pedido.service.adapters.in.web.dto.response;

import java.math.BigDecimal;

public record ItemPedidoResponse(Long itemCardapioId, String nomeItem, int quantidade, BigDecimal precoUnitario,
        BigDecimal subtotal) {
}
