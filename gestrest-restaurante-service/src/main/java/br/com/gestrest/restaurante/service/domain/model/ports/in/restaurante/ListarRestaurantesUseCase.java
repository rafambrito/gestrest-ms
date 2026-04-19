package br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante;

import java.util.List;

import br.com.gestrest.restaurante.service.domain.model.Restaurante;

public interface ListarRestaurantesUseCase {

    List<Restaurante> listar();
}
