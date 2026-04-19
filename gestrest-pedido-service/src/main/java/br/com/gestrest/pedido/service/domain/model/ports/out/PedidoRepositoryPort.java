package br.com.gestrest.pedido.service.domain.model.ports.out;

import java.util.List;
import java.util.Optional;

import br.com.gestrest.pedido.service.domain.model.Pedido;

public interface PedidoRepositoryPort {
    Pedido salvar(Pedido pedido);

    Optional<Pedido> buscarPorId(Long id);

    List<Pedido> listarPorUsuario(Long usuarioId);
}
