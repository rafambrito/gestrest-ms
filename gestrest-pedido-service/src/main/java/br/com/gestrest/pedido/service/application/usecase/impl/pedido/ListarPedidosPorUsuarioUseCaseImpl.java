package br.com.gestrest.pedido.service.application.usecase.impl.pedido;

import java.util.List;

import br.com.gestrest.pedido.service.domain.model.Pedido;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.ListarPedidosPorUsuarioUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoRepositoryPort;

public class ListarPedidosPorUsuarioUseCaseImpl implements ListarPedidosPorUsuarioUseCase {

    private final PedidoRepositoryPort pedidoRepository;

    public ListarPedidosPorUsuarioUseCaseImpl(PedidoRepositoryPort pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listar(Long usuarioId) {
        return pedidoRepository.listarPorUsuario(usuarioId);
    }
}
