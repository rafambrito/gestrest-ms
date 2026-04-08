package br.com.gestrest.pagamento.service.application.usecase.command.pagamento;

import java.math.BigDecimal;

public record ProcessarPagamentoCommand(Long pedidoId, Long usuarioId, BigDecimal valor) {
}
