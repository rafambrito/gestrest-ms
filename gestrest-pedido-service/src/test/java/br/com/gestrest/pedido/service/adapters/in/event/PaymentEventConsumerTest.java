package br.com.gestrest.pedido.service.adapters.in.event;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestrest.pedido.service.domain.model.ItemPedido;
import br.com.gestrest.pedido.service.domain.model.Pedido;
import br.com.gestrest.pedido.service.domain.model.PedidoStatusEnum;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoRepositoryPort;

@ExtendWith(MockitoExtension.class)
class PaymentEventConsumerTest {

    @Mock
    private PedidoRepositoryPort pedidoRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PaymentEventConsumer consumer;

    @Test
    void handlePagamentoAprovado_shouldMarkPedidoAsPago() throws Exception {
        var pedido = Pedido.existente(1L, 1L, 10L, java.util.List.of(ItemPedido.criar(1L, "Pizza", 1, java.math.BigDecimal.valueOf(42.9))), java.math.BigDecimal.valueOf(42.9), PedidoStatusEnum.CRIADO, LocalDateTime.now(), null);

        when(pedidoRepository.buscarPorId(1L)).thenReturn(Optional.of(pedido));

        // build JSON message
        String message = "{\"pedidoId\":1,\"usuarioId\":1,\"timestamp\":\"2023-01-01T00:00:00\"}";

        // use real object mapper to deserialize inside consumer
        consumer = new PaymentEventConsumer(pedidoRepository, new ObjectMapper());
        consumer.handlePagamentoAprovado(message);

        verify(pedidoRepository).salvar(any(Pedido.class));
    }
}
