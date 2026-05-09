package br.com.gestrest.restaurante.service.application.usecase.impl.restaurante;

import br.com.gestrest.restaurante.service.domain.exception.RestauranteNaoEncontradoException;
import br.com.gestrest.restaurante.service.domain.model.Restaurante;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortRead;

public class BuscarRestaurantePorIdUseCaseImpl implements BuscarRestaurantePorIdUseCase {

    private final RestauranteRepositoryPortRead restauranteRepositoryRead;

    public BuscarRestaurantePorIdUseCaseImpl(RestauranteRepositoryPortRead restauranteRepositoryRead) {
        this.restauranteRepositoryRead = restauranteRepositoryRead;
    }

    @Override
    public Restaurante buscar(Long id) {
        return restauranteRepositoryRead.buscarPorId(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }
}
