package br.com.gestrest.restaurante.service.application.usecase.command.itemcardapio;

import java.math.BigDecimal;

public record CriarItemCardapioCommand(String nome, String descricao, BigDecimal preco, Long restauranteId) {
}
