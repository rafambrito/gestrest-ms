package br.com.gestrest.restaurante.service.application.usecase.impl.restaurante;

import br.com.gestrest.restaurante.service.application.usecase.command.restaurante.AtualizarRestauranteCommand;
import br.com.gestrest.restaurante.service.domain.exception.RestauranteNaoEncontradoException;
import br.com.gestrest.restaurante.service.domain.model.Restaurante;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.AtualizarRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortRead;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortWrite;

public class AtualizarRestauranteUseCaseImpl implements AtualizarRestauranteUseCase {

    private final RestauranteRepositoryPortRead restauranteRepositoryRead;
    private final RestauranteRepositoryPortWrite restauranteRepositoryWrite;

    public AtualizarRestauranteUseCaseImpl(
            RestauranteRepositoryPortRead restauranteRepositoryRead,
            RestauranteRepositoryPortWrite restauranteRepositoryWrite) {
        this.restauranteRepositoryRead = restauranteRepositoryRead;
        this.restauranteRepositoryWrite = restauranteRepositoryWrite;
    }

    @Override
    public Restaurante atualizar(Long id, AtualizarRestauranteCommand command) {
        var restaurante = restauranteRepositoryRead.buscarPorId(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));

        restaurante.atualizar(command.nome(), command.ativo());
        return restauranteRepositoryWrite.salvar(restaurante);
    }
}
