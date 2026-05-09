package br.com.gestrest.restaurante.service.application.usecase.impl.restaurante;

import java.util.List;

import br.com.gestrest.restaurante.service.domain.model.Restaurante;

public interface ListarRestauranteUseCase {
  List<Restaurante> listar();
}
