package br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante;

import br.com.gestrest.restaurante.service.domain.model.Restaurante;

public interface BuscarRestaurantePorIdUseCase {

    Restaurante buscar(Long id);
}
