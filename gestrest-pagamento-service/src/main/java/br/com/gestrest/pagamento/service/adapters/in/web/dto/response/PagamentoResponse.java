package br.com.gestrest.pagamento.service.adapters.in.web.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.gestrest.pagamento.service.domain.model.PagamentoStatusEnum;

public record PagamentoResponse(Long id, Long pedidoId, Long usuarioId, BigDecimal valor, PagamentoStatusEnum status,
        LocalDateTime dataCriacao) {
}
