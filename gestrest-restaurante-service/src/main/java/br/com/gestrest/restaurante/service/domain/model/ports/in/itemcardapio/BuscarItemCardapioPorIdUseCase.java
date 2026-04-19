package br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio;

import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;

public interface BuscarItemCardapioPorIdUseCase {

    ItemCardapio buscar(Long id);
}
