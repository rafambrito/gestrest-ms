package br.com.gestrest.pagamento.service.adapters.out.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestrest.pagamento.service.domain.model.event.PagamentoAprovadoEvent;
import br.com.gestrest.pagamento.service.domain.model.event.PagamentoPendenteEvent;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoEventPublisherPort;

@Component
public class KafkaEventPublisherAdapter implements PagamentoEventPublisherPort {

    private static final String TOPIC_PAGAMENTO_APROVADO = "pagamento.aprovado";
    private static final String TOPIC_PAGAMENTO_PENDENTE = "pagamento.pendente";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaEventPublisherAdapter(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publicarAprovado(PagamentoAprovadoEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC_PAGAMENTO_APROVADO, event.pedidoId().toString(), message);
        } catch (Exception e) {
            System.err.println("Falha ao publicar evento PagamentoAprovado: " + e.getMessage());
        }
    }

    @Override
    public void publicarPendente(PagamentoPendenteEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC_PAGAMENTO_PENDENTE, event.pedidoId().toString(), message);
        } catch (Exception e) {
            System.err.println("Falha ao publicar evento PagamentoPendente: " + e.getMessage());
        }
    }
}
