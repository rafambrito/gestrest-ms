package br.com.gestrest.restaurante.service.application.usecase.impl.restaurante;

import java.util.List;

import br.com.gestrest.restaurante.service.domain.model.Restaurante;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.ListarRestaurantesUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortRead;

public class ListarRestaurantesUseCaseImpl implements ListarRestaurantesUseCase {

    private final RestauranteRepositoryPortRead restauranteRepositoryRead;

    public ListarRestaurantesUseCaseImpl(RestauranteRepositoryPortRead restauranteRepositoryRead) {
        this.restauranteRepositoryRead = restauranteRepositoryRead;
    }

    @Override
    public List<Restaurante> listar() {
        return restauranteRepositoryRead.listar();
    }
}
