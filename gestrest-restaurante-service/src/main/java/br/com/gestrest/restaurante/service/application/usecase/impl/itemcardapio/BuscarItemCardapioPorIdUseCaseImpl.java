package br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio;

import br.com.gestrest.restaurante.service.domain.exception.EntityNotFoundException;
import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.BuscarItemCardapioPorIdUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortRead;

public class BuscarItemCardapioPorIdUseCaseImpl implements BuscarItemCardapioPorIdUseCase {

    private final ItemCardapioRepositoryPortRead itemCardapioRepositoryRead;

    public BuscarItemCardapioPorIdUseCaseImpl(ItemCardapioRepositoryPortRead itemCardapioRepositoryRead) {
        this.itemCardapioRepositoryRead = itemCardapioRepositoryRead;
    }

    @Override
    public ItemCardapio buscar(Long id) {
        return itemCardapioRepositoryRead.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "ItemCardapio"));
    }
}
