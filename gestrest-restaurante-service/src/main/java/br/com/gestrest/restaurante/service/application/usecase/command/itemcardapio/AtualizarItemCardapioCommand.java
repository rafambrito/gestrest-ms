package br.com.gestrest.restaurante.service.application.usecase.command.itemcardapio;

import java.math.BigDecimal;

public record AtualizarItemCardapioCommand(
        String nome,
        String descricao,
        BigDecimal preco,
        boolean ativo,
        Long restauranteId) {
}
