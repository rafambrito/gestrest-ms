package br.com.gestrest.pagamento.service.adapters.out.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestrest.pagamento.service.domain.model.event.PagamentoAprovadoEvent;
import br.com.gestrest.pagamento.service.domain.model.event.PagamentoPendenteEvent;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoEventPublisherPort;
import br.com.gestrest.pagamento.service.infrastructure.config.KafkaTopics;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaEventPublisherAdapter implements PagamentoEventPublisherPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaEventPublisherAdapter(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publicarAprovado(PagamentoAprovadoEvent event) {
        try {
            String message = java.util.Objects.requireNonNull(objectMapper.writeValueAsString(event));
            String key = java.util.Objects.requireNonNull(String.valueOf(event.pedidoId()));
            kafkaTemplate.send(KafkaTopics.PAGAMENTO_APROVADO, key, message).get();
            log.info("Evento pagamento.aprovado enviado. pedidoId={}", event.pedidoId());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Publicacao do evento pagamento.aprovado foi interrompida", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Falha ao publicar evento pagamento.aprovado. pedidoId=" + event.pedidoId(), ex);
        }
    }

    @Override
    public void publicarPendente(PagamentoPendenteEvent event) {
        try {
            String message = java.util.Objects.requireNonNull(objectMapper.writeValueAsString(event));
            String key = java.util.Objects.requireNonNull(String.valueOf(event.pedidoId()));
            kafkaTemplate.send(KafkaTopics.PAGAMENTO_PENDENTE, key, message).get();
            log.info("Evento pagamento.pendente enviado. pedidoId={}", event.pedidoId());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Publicacao do evento pagamento.pendente foi interrompida", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Falha ao publicar evento pagamento.pendente. pedidoId=" + event.pedidoId(), ex);
        }
    }
}
