package br.com.gestrest.pagamento.service.domain.model.event;

import java.time.LocalDateTime;

public record PagamentoPendenteEvent(
    Long pedidoId,
    Long usuarioId,
    LocalDateTime timestamp
) {}
