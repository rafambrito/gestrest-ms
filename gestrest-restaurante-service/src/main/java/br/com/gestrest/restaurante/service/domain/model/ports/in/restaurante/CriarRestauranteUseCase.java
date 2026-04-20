package br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante;

import br.com.gestrest.restaurante.service.application.usecase.command.restaurante.CriarRestauranteCommand;
import br.com.gestrest.restaurante.service.domain.model.Restaurante;

public interface CriarRestauranteUseCase {

    Restaurante criar(CriarRestauranteCommand command);
}
