package br.com.gestrest.api.application.usecase.impl.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gestrest.pedido.service.application.usecase.command.pedido.CriarPedidoCommand;
import br.com.gestrest.pedido.service.application.usecase.command.pedido.CriarPedidoCommand.ItemPedidoCommand;
import br.com.gestrest.pedido.service.application.usecase.impl.pedido.CriarPedidoUseCaseImpl;
import br.com.gestrest.pedido.service.domain.model.Pedido;
import br.com.gestrest.pedido.service.domain.model.PedidoStatusEnum;
import br.com.gestrest.pedido.service.domain.model.ports.out.PagamentoClientPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PagamentoEventPublisherPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoEventPublisherPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoRepositoryPort;

@ExtendWith(MockitoExtension.class)
@DisplayName("CriarPedidoUseCaseImpl Testes")
class CriarPedidoUseCaseImplTest {

    @Mock
    private PedidoRepositoryPort pedidoRepository;

    @Mock
    private PagamentoClientPort pagamentoProcessador;

    @Mock
    private PedidoEventPublisherPort pedidoEventPublisher;

    @Mock
    private PagamentoEventPublisherPort pagamentoEventPublisher;

    @InjectMocks
    private CriarPedidoUseCaseImpl useCase;

    @Test
    @DisplayName("Deve criar pedido e marcar pago quando pagamento for aprovado")
    void deveCriarPedidoEMarcarPagoQuandoPagamentoForAprovado() {
        var command = new CriarPedidoCommand(1L, 10L, List.of(new ItemPedidoCommand(1L, "Pizza", 2, new BigDecimal("42.90"))));

        when(pedidoRepository.salvar(any(Pedido.class))).thenAnswer(invocation -> {
            var pedido = invocation.getArgument(0, Pedido.class);
            if (pedido.getId() == null) {
                return Pedido.existente(1L, pedido.getUsuarioId(), pedido.getRestauranteId(), pedido.getItens(), pedido.getValorTotal(), pedido.getStatus(), pedido.getDataCriacao(), pedido.getDataUltimaAlteracao());
            }
            return pedido;
        });
        when(pagamentoProcessador.processar(any(Long.class), any(Long.class), any(BigDecimal.class))).thenReturn(true);

        var criado = useCase.criar(command);

        assertEquals(1L, criado.getId());
        assertEquals(PedidoStatusEnum.PAGO, criado.getStatus());
        assertEquals(0, criado.getValorTotal().compareTo(new BigDecimal("85.80")));
        verify(pagamentoEventPublisher).publicarPagamentoAprovado(1L, 1L);
    }

    @Test
    @DisplayName("Deve criar pedido e marcar pendente pagamento quando pagamento for rejeitado")
    void deveCriarPedidoEMarcarPendentePagamentoQuandoPagamentoForRejeitado() {
        var command = new CriarPedidoCommand(1L, 10L, List.of(new ItemPedidoCommand(1L, "Pizza", 1, new BigDecimal("42.90"))));

        when(pedidoRepository.salvar(any(Pedido.class))).thenAnswer(invocation -> {
            var pedido = invocation.getArgument(0, Pedido.class);
            if (pedido.getId() == null) {
                return Pedido.existente(1L, pedido.getUsuarioId(), pedido.getRestauranteId(), pedido.getItens(), pedido.getValorTotal(), pedido.getStatus(), pedido.getDataCriacao(), pedido.getDataUltimaAlteracao());
            }
            return pedido;
        });
        when(pagamentoProcessador.processar(any(Long.class), any(Long.class), any(BigDecimal.class))).thenReturn(false);

        var criado = useCase.criar(command);

        assertEquals(1L, criado.getId());
        assertEquals(PedidoStatusEnum.PENDENTE_PAGAMENTO, criado.getStatus());
        verify(pagamentoEventPublisher).publicarPagamentoPendente(1L, 1L);
    }
}
