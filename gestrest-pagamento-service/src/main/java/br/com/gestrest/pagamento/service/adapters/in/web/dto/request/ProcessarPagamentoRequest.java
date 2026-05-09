package br.com.gestrest.pagamento.service.adapters.in.web.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProcessarPagamentoRequest(
        @NotNull(message = "Pedido e obrigatorio") Long pedidoId,
        @NotNull(message = "Usuario e obrigatorio") Long usuarioId,
        @NotNull(message = "Valor e obrigatorio") @Positive(message = "Valor deve ser maior que zero") BigDecimal valor) {
}
