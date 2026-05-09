package br.com.gestrest.pagamento.service.application.usecase.impl.pagamento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gestrest.pagamento.service.application.usecase.command.pagamento.ProcessarPagamentoCommand;
import br.com.gestrest.pagamento.service.domain.model.Pagamento;
import br.com.gestrest.pagamento.service.domain.model.PagamentoStatusEnum;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoEventPublisherPort;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoGatewayPort;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoGatewayResponse;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoRepositoryPort;

@ExtendWith(MockitoExtension.class)
class ProcessarPagamentoUseCaseImplTest {

    private ProcessarPagamentoUseCaseImpl useCase;

    @Mock
    private PagamentoRepositoryPort pagamentoRepository;

    @Mock
    private PagamentoGatewayPort pagamentoGateway;

    @Mock
    private PagamentoEventPublisherPort pagamentoEventPublisher;

    @BeforeEach
    void setUp() {
        useCase = new ProcessarPagamentoUseCaseImpl(pagamentoRepository, pagamentoGateway, pagamentoEventPublisher);
        lenient().when(pagamentoRepository.salvar(any(Pagamento.class))).thenAnswer(invocation -> {
            var pagamento = invocation.getArgument(0, Pagamento.class);
            if (pagamento.getId() == null) {
                return Pagamento.existente(1L, pagamento.getPedidoId(), pagamento.getUsuarioId(), pagamento.getValor(), pagamento.getStatus(), pagamento.getPagamentoIdExterno(), pagamento.getDataCriacao());
            }
            return pagamento;
        });
    }

    @Test
    void deveLançarExcecaoQuandoPedidoIdNulo() {
        var command = new ProcessarPagamentoCommand(null, 1L, BigDecimal.valueOf(100));
        assertThrows(IllegalArgumentException.class, () -> useCase.processar(command));
    }

    @Test
    void deveLançarExcecaoQuandoUsuarioIdNulo() {
        var command = new ProcessarPagamentoCommand(1L, null, BigDecimal.valueOf(100));
        assertThrows(IllegalArgumentException.class, () -> useCase.processar(command));
    }

    @Test
    void deveLançarExcecaoQuandoValorInvalido() {
        var command = new ProcessarPagamentoCommand(1L, 1L, BigDecimal.ZERO);
        assertThrows(IllegalArgumentException.class, () -> useCase.processar(command));
    }

    @Test
    void deveAprovarPagamentoQuandoGatewayRetornaSucesso() {
        var command = new ProcessarPagamentoCommand(1L, 1L, BigDecimal.valueOf(100));
        when(pagamentoGateway.iniciarPagamento(anyLong(), anyLong(), any(BigDecimal.class)))
            .thenReturn(new PagamentoGatewayResponse("ext-123", "sucesso"));
        when(pagamentoGateway.consultarStatus("ext-123")).thenReturn("sucesso");

        var resultado = useCase.processar(command);

        assertNotNull(resultado);
        assertEquals(PagamentoStatusEnum.APROVADO, resultado.getStatus());
        assertEquals("ext-123", resultado.getPagamentoIdExterno());
        verify(pagamentoEventPublisher).publicarAprovado(any());
    }

    @Test
    void deveMarcarComoPendenteQuandoGatewayRetornaFalha() {
        var command = new ProcessarPagamentoCommand(1L, 1L, BigDecimal.valueOf(100));
        when(pagamentoGateway.iniciarPagamento(anyLong(), anyLong(), any(BigDecimal.class)))
            .thenReturn(new PagamentoGatewayResponse("ext-456", "falha"));
        when(pagamentoGateway.consultarStatus("ext-456")).thenReturn("falha");

        var resultado = useCase.processar(command);

        assertNotNull(resultado);
        assertEquals(PagamentoStatusEnum.PENDENTE, resultado.getStatus());
        assertEquals("ext-456", resultado.getPagamentoIdExterno());
        verify(pagamentoEventPublisher).publicarPendente(any());
    }

    @Test
    void deveMarcarComoPendenteQuandoGatewayLançaExcecao() {
        var command = new ProcessarPagamentoCommand(1L, 1L, BigDecimal.valueOf(100));
        when(pagamentoGateway.iniciarPagamento(anyLong(), anyLong(), any(BigDecimal.class)))
            .thenThrow(new RuntimeException("Erro na chamada ao gateway"));

        var resultado = useCase.processar(command);

        assertNotNull(resultado);
        assertEquals(PagamentoStatusEnum.PENDENTE, resultado.getStatus());
        verify(pagamentoEventPublisher).publicarPendente(any());
    }
}
