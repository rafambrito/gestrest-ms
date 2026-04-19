package br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante;

import br.com.gestrest.restaurante.service.application.usecase.command.restaurante.AtualizarRestauranteCommand;
import br.com.gestrest.restaurante.service.domain.model.Restaurante;

public interface AtualizarRestauranteUseCase {

    Restaurante atualizar(Long id, AtualizarRestauranteCommand command);
}
