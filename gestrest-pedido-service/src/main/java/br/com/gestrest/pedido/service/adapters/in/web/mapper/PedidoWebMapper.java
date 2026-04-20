package br.com.gestrest.pedido.service.adapters.in.web.mapper;

import org.springframework.stereotype.Component;

import br.com.gestrest.pedido.service.adapters.in.web.dto.request.CriarPedidoRequest;
import br.com.gestrest.pedido.service.adapters.in.web.dto.response.ItemPedidoResponse;
import br.com.gestrest.pedido.service.adapters.in.web.dto.response.PedidoResponse;
import br.com.gestrest.pedido.service.application.usecase.command.pedido.CriarPedidoCommand;
import br.com.gestrest.pedido.service.domain.model.Pedido;

@Component
public class PedidoWebMapper {

    public CriarPedidoCommand toCommand(CriarPedidoRequest request) {
        return new CriarPedidoCommand(
                request.usuarioId(),
                request.restauranteId(),
                request.itens().stream()
                        .map(i -> new CriarPedidoCommand.ItemPedidoCommand(i.itemCardapioId(), i.nomeItem(), i.quantidade(),
                                i.precoUnitario()))
                        .toList());
    }

    public PedidoResponse toResponse(Pedido pedido) {
        var itens = pedido.getItens().stream()
                .map(i -> new ItemPedidoResponse(i.getItemCardapioId(), i.getNomeItem(), i.getQuantidade(),
                        i.getPrecoUnitario(), i.subtotal()))
                .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getUsuarioId(),
                pedido.getRestauranteId(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                itens,
                pedido.getDataCriacao(),
                pedido.getDataUltimaAlteracao());
    }
}
