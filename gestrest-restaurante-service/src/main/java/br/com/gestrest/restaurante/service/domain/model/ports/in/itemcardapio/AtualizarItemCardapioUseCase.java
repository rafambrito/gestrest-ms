package br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio;

import br.com.gestrest.restaurante.service.application.usecase.command.itemcardapio.AtualizarItemCardapioCommand;
import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;

public interface AtualizarItemCardapioUseCase {

    ItemCardapio atualizar(Long id, AtualizarItemCardapioCommand command);
}
