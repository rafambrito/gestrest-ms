package br.com.gestrest.pedido.service.adapters.out.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestrest.pedido.service.domain.model.event.PedidoCriadoEvent;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoEventPublisherPort;
import br.com.gestrest.pedido.service.infrastructure.config.KafkaTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MockPedidoEventPublisherAdapter implements PedidoEventPublisherPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publicarPedidoCriado(PedidoCriadoEvent event) {
        try {
            var message = java.util.Objects.requireNonNull(objectMapper.writeValueAsString(event));
            String key = java.util.Objects.requireNonNull(String.valueOf(event.pedidoId()));
            kafkaTemplate.send(KafkaTopics.PEDIDO_CRIADO, key, message).get();
            log.info("Publicado evento pedido.criado pedidoId={}", event.pedidoId());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Publicacao do evento pedido.criado foi interrompida", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Falha ao publicar evento pedido.criado. pedidoId=" + event.pedidoId(), ex);
        }
    }
}
