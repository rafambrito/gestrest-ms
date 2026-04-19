package br.com.gestrest.pedido.service.application.usecase.impl.pedido;

import java.util.List;

import br.com.gestrest.pedido.service.application.usecase.command.pedido.CriarPedidoCommand;
import br.com.gestrest.pedido.service.domain.model.ItemPedido;
import br.com.gestrest.pedido.service.domain.model.Pedido;
import br.com.gestrest.pedido.service.domain.model.event.PedidoCriadoEvent;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.CriarPedidoUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.PagamentoEventPublisherPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PagamentoProcessadorPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoEventPublisherPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoRepositoryPort;

public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {

    private final PedidoRepositoryPort pedidoRepository;
    private final PagamentoProcessadorPort pagamentoProcessador;
    private final PedidoEventPublisherPort pedidoEventPublisher;
    private final PagamentoEventPublisherPort pagamentoEventPublisher;

    public CriarPedidoUseCaseImpl(PedidoRepositoryPort pedidoRepository, PagamentoProcessadorPort pagamentoProcessador,
            PedidoEventPublisherPort pedidoEventPublisher, PagamentoEventPublisherPort pagamentoEventPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.pagamentoProcessador = pagamentoProcessador;
        this.pedidoEventPublisher = pedidoEventPublisher;
        this.pagamentoEventPublisher = pagamentoEventPublisher;
    }

    @Override
    public Pedido criar(CriarPedidoCommand command) {
        List<ItemPedido> itens = command.itens().stream()
                .map(i -> ItemPedido.criar(i.itemCardapioId(), i.nomeItem(), i.quantidade(), i.precoUnitario()))
                .toList();

        Pedido pedido = Pedido.criar(command.usuarioId(), command.restauranteId(), itens);
        Pedido salvo = pedidoRepository.salvar(pedido);

        pedidoEventPublisher.publicarPedidoCriado(
                new PedidoCriadoEvent(salvo.getId(), salvo.getUsuarioId(), salvo.getRestauranteId(), salvo.getValorTotal()));

        try {
            boolean aprovado = pagamentoProcessador.processar(salvo.getId(), salvo.getUsuarioId(), salvo.getValorTotal());
            if (aprovado) {
                salvo.marcarPago();
                pagamentoEventPublisher.publicarPagamentoAprovado(salvo.getId(), salvo.getUsuarioId());
            } else {
                salvo.marcarPendentePagamento();
                pagamentoEventPublisher.publicarPagamentoPendente(salvo.getId(), salvo.getUsuarioId());
            }
        } catch (RuntimeException ex) {
            salvo.marcarPendentePagamento();
            pagamentoEventPublisher.publicarPagamentoPendente(salvo.getId(), salvo.getUsuarioId());
        }

        return pedidoRepository.salvar(salvo);
    }
}
