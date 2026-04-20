package br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio;

import java.util.List;

import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;

public interface ListarItensPorRestauranteUseCase {

    List<ItemCardapio> listar(Long restauranteId);
}
