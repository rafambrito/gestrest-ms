package br.com.gestrest.pagamento.service.adapters.in.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestrest.pagamento.service.domain.model.event.PedidoCriadoEvent;
import br.com.gestrest.pagamento.service.domain.model.ports.in.PedidoEventConsumerPort;
import br.com.gestrest.pagamento.service.infrastructure.config.KafkaTopics;

@Component
public class PedidoEventConsumer {

    private final ObjectMapper objectMapper;
    private final PedidoEventConsumerPort pedidoEventConsumerPort;

    public PedidoEventConsumer(ObjectMapper objectMapper, PedidoEventConsumerPort pedidoEventConsumerPort) {
        this.objectMapper = objectMapper;
        this.pedidoEventConsumerPort = pedidoEventConsumerPort;
    }

    @Transactional
    @KafkaListener(topics = KafkaTopics.PEDIDO_CRIADO, groupId = "pagamento-service")
    public void consumirPedidoCriado(String message) {
        try {
            var event = objectMapper.readValue(message, PedidoCriadoEvent.class);
            pedidoEventConsumerPort.onPedidoCriado(event);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar evento pedido.criado", e);
        }
    }
}
