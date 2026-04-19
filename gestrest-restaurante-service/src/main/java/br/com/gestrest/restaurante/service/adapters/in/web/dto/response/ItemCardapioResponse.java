package br.com.gestrest.restaurante.service.adapters.in.web.dto.response;

import java.math.BigDecimal;

public record ItemCardapioResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        boolean ativo,
        Long restauranteId) {
}
