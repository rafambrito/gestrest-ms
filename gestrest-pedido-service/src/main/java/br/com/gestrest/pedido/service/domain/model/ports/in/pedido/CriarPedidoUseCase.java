package br.com.gestrest.pedido.service.domain.model.ports.in.pedido;

import br.com.gestrest.pedido.service.application.usecase.command.pedido.CriarPedidoCommand;
import br.com.gestrest.pedido.service.domain.model.Pedido;

public interface CriarPedidoUseCase {
    Pedido criar(CriarPedidoCommand command);
}
