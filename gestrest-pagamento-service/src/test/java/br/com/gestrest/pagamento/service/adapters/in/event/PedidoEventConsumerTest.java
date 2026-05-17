package br.com.gestrest.pagamento.service.adapters.in.event;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.gestrest.pagamento.service.domain.model.event.PedidoCriadoEvent;
import br.com.gestrest.pagamento.service.domain.model.ports.in.PedidoEventConsumerPort;

@ExtendWith(MockitoExtension.class)
class PedidoEventConsumerTest {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Mock
    private PedidoEventConsumerPort pedidoEventConsumerPort;

    @Test
    void shouldDelegatePedidoCriadoEventToUseCase() throws Exception {
        var event = new PedidoCriadoEvent(1L, 2L, 3L, BigDecimal.valueOf(125.50), LocalDateTime.now());
        var message = objectMapper.writeValueAsString(event);

        var consumer = new PedidoEventConsumer(objectMapper, pedidoEventConsumerPort);
        consumer.consumirPedidoCriado(message);

        verify(pedidoEventConsumerPort).onPedidoCriado(event);
    }
}
