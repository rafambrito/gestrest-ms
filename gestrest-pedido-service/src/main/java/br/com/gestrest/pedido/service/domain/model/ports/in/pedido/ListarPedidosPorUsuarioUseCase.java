package br.com.gestrest.pedido.service.domain.model.ports.in.pedido;

import java.util.List;

import br.com.gestrest.pedido.service.domain.model.Pedido;

public interface ListarPedidosPorUsuarioUseCase {
    List<Pedido> listar(Long usuarioId);
}
