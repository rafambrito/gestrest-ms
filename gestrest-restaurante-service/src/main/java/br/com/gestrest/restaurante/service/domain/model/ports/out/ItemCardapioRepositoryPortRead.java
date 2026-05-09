package br.com.gestrest.restaurante.service.domain.model.ports.out;

import java.util.List;
import java.util.Optional;

import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;

public interface ItemCardapioRepositoryPortRead {

    Optional<ItemCardapio> buscarPorId(Long id);

    List<ItemCardapio> listarPorRestauranteId(Long restauranteId);
}
