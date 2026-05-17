package br.com.gestrest.pedido.service.application.usecase.impl.pedido;

import java.time.LocalDateTime;
import java.util.List;

import br.com.gestrest.pedido.service.application.usecase.command.pedido.CriarPedidoCommand;
import br.com.gestrest.pedido.service.domain.model.ItemPedido;
import br.com.gestrest.pedido.service.domain.model.Pedido;
import br.com.gestrest.pedido.service.domain.model.event.PedidoCriadoEvent;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.CriarPedidoUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoEventPublisherPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoRepositoryPort;

public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {

    private final PedidoRepositoryPort pedidoRepository;
    private final PedidoEventPublisherPort pedidoEventPublisher;

    public CriarPedidoUseCaseImpl(PedidoRepositoryPort pedidoRepository,
            PedidoEventPublisherPort pedidoEventPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoEventPublisher = pedidoEventPublisher;
    }

    @Override
    public Pedido criar(CriarPedidoCommand command) {
        List<ItemPedido> itens = command.itens().stream()
                .map(i -> ItemPedido.criar(i.itemCardapioId(), i.nomeItem(), i.quantidade(), i.precoUnitario()))
                .toList();

        Pedido pedido = Pedido.criar(command.usuarioId(), command.restauranteId(), itens);
        Pedido salvo = pedidoRepository.salvar(pedido);

        // marcar como pendente de pagamento e persistir antes de publicar o evento
        salvo.marcarPendentePagamento();
        pedidoRepository.salvar(salvo);

        pedidoEventPublisher.publicarPedidoCriado(
                new PedidoCriadoEvent(
                        salvo.getId(),
                        salvo.getRestauranteId(),
                        salvo.getUsuarioId(),
                        salvo.getValorTotal(),
                        LocalDateTime.now()));

        return salvo;
    }
}
