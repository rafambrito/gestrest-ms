package br.com.gestrest.pedido.service.domain.model.event;

import java.math.BigDecimal;

public record PedidoCriadoEvent(Long pedidoId, Long usuarioId, Long restauranteId, BigDecimal valorTotal) {
}
