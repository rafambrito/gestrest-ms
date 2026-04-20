package br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio;

import java.util.List;

import br.com.gestrest.restaurante.service.domain.exception.RestauranteNaoEncontradoException;
import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.ListarItensPorRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortRead;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortRead;

public class ListarItensPorRestauranteUseCaseImpl implements ListarItensPorRestauranteUseCase {

    private final ItemCardapioRepositoryPortRead itemCardapioRepositoryRead;
    private final RestauranteRepositoryPortRead restauranteRepositoryRead;

    public ListarItensPorRestauranteUseCaseImpl(
            ItemCardapioRepositoryPortRead itemCardapioRepositoryRead,
            RestauranteRepositoryPortRead restauranteRepositoryRead) {
        this.itemCardapioRepositoryRead = itemCardapioRepositoryRead;
        this.restauranteRepositoryRead = restauranteRepositoryRead;
    }

    @Override
    public List<ItemCardapio> listar(Long restauranteId) {
        if (restauranteRepositoryRead.buscarPorId(restauranteId).isEmpty()) {
            throw new RestauranteNaoEncontradoException(restauranteId);
        }

        return itemCardapioRepositoryRead.listarPorRestauranteId(restauranteId);
    }
}
