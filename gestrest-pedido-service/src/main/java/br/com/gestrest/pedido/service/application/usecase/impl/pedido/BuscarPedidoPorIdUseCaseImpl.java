package br.com.gestrest.pedido.service.application.usecase.impl.pedido;

import br.com.gestrest.pedido.service.domain.exception.PedidoNaoEncontradoException;
import br.com.gestrest.pedido.service.domain.model.Pedido;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.BuscarPedidoPorIdUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoRepositoryPort;

public class BuscarPedidoPorIdUseCaseImpl implements BuscarPedidoPorIdUseCase {

    private final PedidoRepositoryPort pedidoRepository;

    public BuscarPedidoPorIdUseCaseImpl(PedidoRepositoryPort pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido buscar(Long id) {
        return pedidoRepository.buscarPorId(id).orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }
}
