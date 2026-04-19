package br.com.gestrest.restaurante.service.application.usecase.impl.restaurante;

import br.com.gestrest.restaurante.service.application.usecase.command.restaurante.CriarRestauranteCommand;
import br.com.gestrest.restaurante.service.domain.model.Restaurante;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.CriarRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortWrite;

public class CriarRestauranteUseCaseImpl implements CriarRestauranteUseCase {

    private final RestauranteRepositoryPortWrite restauranteRepositoryWrite;

    public CriarRestauranteUseCaseImpl(RestauranteRepositoryPortWrite restauranteRepositoryWrite) {
        this.restauranteRepositoryWrite = restauranteRepositoryWrite;
    }

    @Override
    public Restaurante criar(CriarRestauranteCommand command) {
        var restaurante = Restaurante.criar(command.nome());
        return restauranteRepositoryWrite.salvar(restaurante);
    }
}
