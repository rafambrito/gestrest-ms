package br.com.gestrest.restaurante.service.adapters.in.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestrest.restaurante.service.domain.model.event.PedidoCriadoEvent;
import br.com.gestrest.restaurante.service.domain.model.ports.in.PedidoEventConsumerPort;

@Component
public class PedidoEventConsumer {

    private final ObjectMapper objectMapper;
    private final PedidoEventConsumerPort pedidoEventConsumerPort;

    public PedidoEventConsumer(ObjectMapper objectMapper, PedidoEventConsumerPort pedidoEventConsumerPort) {
        this.objectMapper = objectMapper;
        this.pedidoEventConsumerPort = pedidoEventConsumerPort;
    }

    @KafkaListener(topics = "pedido.criado", groupId = "restaurante-service")
    public void consumirPedidoCriado(String message) {
        try {
            var event = objectMapper.readValue(message, PedidoCriadoEvent.class);
            pedidoEventConsumerPort.onPedidoCriado(event);
        } catch (Exception e) {
            System.err.println("Erro ao processar evento pedido.criado: " + e.getMessage());
        }
    }
}
