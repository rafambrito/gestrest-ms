package br.com.gestrest.pedido.service.adapters.in.event;

import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gestrest.pedido.service.domain.model.Pedido;
import br.com.gestrest.pedido.service.domain.model.PedidoStatusEnum;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoRepositoryPort;
import br.com.gestrest.pedido.service.infrastructure.config.KafkaTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {

    private final PedidoRepositoryPort pedidoRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    @KafkaListener(topics = KafkaTopics.PAGAMENTO_APROVADO, groupId = "${spring.kafka.consumer.group-id:pedido-service-group}")
    public void handlePagamentoAprovado(String message) {
        try {
            JsonNode node = objectMapper.readTree(message);
            Long pedidoId = node.get("pedidoId").asLong();
            log.info("Consumindo evento pagamento.aprovado pedidoId={}", pedidoId);
            Optional<Pedido> opt = pedidoRepository.buscarPorId(pedidoId);
            if (opt.isEmpty()) {
                log.warn("Pedido nao encontrado para pagamento.aprovado pedidoId={}", pedidoId);
                return;
            }
            Pedido pedido = opt.get();
            pedido.marcarPago();
            pedidoRepository.salvar(pedido);
            log.info("Pedido marcado como PAGO pedidoId={}", pedidoId);
        } catch (Exception e) {
            log.error("Erro ao processar evento pagamento.aprovado: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao processar evento pagamento.aprovado", e);
        }
    }

    @Transactional
    @KafkaListener(topics = KafkaTopics.PAGAMENTO_PENDENTE, groupId = "${spring.kafka.consumer.group-id:pedido-service-group}")
    public void handlePagamentoPendente(String message) {
        try {
            JsonNode node = objectMapper.readTree(message);
            Long pedidoId = node.get("pedidoId").asLong();
            log.info("Consumindo evento pagamento.pendente pedidoId={}", pedidoId);
            Optional<Pedido> opt = pedidoRepository.buscarPorId(pedidoId);
            if (opt.isEmpty()) {
                log.warn("Pedido nao encontrado para pagamento.pendente pedidoId={}", pedidoId);
                return;
            }

            Pedido pedido = opt.get();

            if (pedido.getStatus() == PedidoStatusEnum.PENDENTE_PAGAMENTO) {
                log.info("Pedido {} já se encontra em estado PENDENTE_PAGAMENTO. Evento ignorado por idempotência.",
                        pedido.getId());
                return;
            }

            pedido.marcarPendentePagamento();
            pedidoRepository.salvar(pedido);
            log.info("Pedido marcado como PENDENTE_PAGAMENTO pedidoId={}", pedidoId);
        } catch (Exception e) {
            log.error("Erro ao processar evento pagamento.pendente: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao processar evento pagamento.pendente", e);
        }
    }
}
