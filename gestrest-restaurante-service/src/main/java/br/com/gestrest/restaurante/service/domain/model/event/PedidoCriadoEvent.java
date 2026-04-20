package br.com.gestrest.restaurante.service.domain.model.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoCriadoEvent(
    Long pedidoId,
    Long restauranteId,
    Long usuarioId,
    BigDecimal valor,
    LocalDateTime timestamp
) {}