package br.com.gestrest.pagamento.service.domain.model.event;

import java.time.LocalDateTime;

public record PagamentoReprocessadoEvent(
        Long pedidoId,
        Long usuarioId,
        String motivo,
        LocalDateTime timestamp) {
}
