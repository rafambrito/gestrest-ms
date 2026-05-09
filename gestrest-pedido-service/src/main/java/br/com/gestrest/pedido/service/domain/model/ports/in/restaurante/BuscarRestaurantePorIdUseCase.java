package br.com.gestrest.pedido.service.domain.model.ports.in.restaurante;

import br.com.gestrest.pedido.service.domain.model.Restaurante;

public interface BuscarRestaurantePorIdUseCase {
	Restaurante executar(Long id);
}
