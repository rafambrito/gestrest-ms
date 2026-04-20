package br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio;

import br.com.gestrest.restaurante.service.application.usecase.command.itemcardapio.CriarItemCardapioCommand;
import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;

public interface CriarItemCardapioUseCase {

    ItemCardapio criar(CriarItemCardapioCommand command);
}
