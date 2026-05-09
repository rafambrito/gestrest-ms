package br.com.gestrest.pagamento.service.domain.model.event;

import java.time.LocalDateTime;

public record PagamentoAprovadoEvent(
    Long pedidoId,
    Long usuarioId,
    LocalDateTime timestamp
) {}
