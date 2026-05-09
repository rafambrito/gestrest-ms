package br.com.gestrest.pedido.service.domain.model.ports.in.pedido;

import br.com.gestrest.pedido.service.domain.model.Pedido;

public interface BuscarPedidoPorIdUseCase {
    Pedido buscar(Long id);
}
