package br.com.gestrest.restaurante.service.application.usecase.impl.restaurante;

import br.com.gestrest.restaurante.service.domain.exception.RestauranteNaoEncontradoException;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.ExcluirRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortWrite;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortRead;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortWrite;

public class ExcluirRestauranteUseCaseImpl implements ExcluirRestauranteUseCase {

    private final RestauranteRepositoryPortRead restauranteRepositoryRead;
    private final RestauranteRepositoryPortWrite restauranteRepositoryWrite;
    private final ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite;

    public ExcluirRestauranteUseCaseImpl(
            RestauranteRepositoryPortRead restauranteRepositoryRead,
            RestauranteRepositoryPortWrite restauranteRepositoryWrite,
            ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite) {
        this.restauranteRepositoryRead = restauranteRepositoryRead;
        this.restauranteRepositoryWrite = restauranteRepositoryWrite;
        this.itemCardapioRepositoryWrite = itemCardapioRepositoryWrite;
    }

    @Override
    public void excluir(Long id) {
        if (restauranteRepositoryRead.buscarPorId(id).isEmpty()) {
            throw new RestauranteNaoEncontradoException(id);
        }

        itemCardapioRepositoryWrite.deletarPorRestauranteId(id);
        restauranteRepositoryWrite.deletar(id);
    }
}
