package br.com.gestrest.pedido.service.application.usecase.command.pedido;

import java.math.BigDecimal;
import java.util.List;

public record CriarPedidoCommand(Long usuarioId, Long restauranteId, List<ItemPedidoCommand> itens) {
    public record ItemPedidoCommand(Long itemCardapioId, String nomeItem, int quantidade, BigDecimal precoUnitario) {
    }
}
