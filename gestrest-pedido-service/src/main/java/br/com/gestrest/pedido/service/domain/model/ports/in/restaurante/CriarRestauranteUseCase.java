package br.com.gestrest.pedido.service.domain.model.ports.in.restaurante;

import br.com.gestrest.pedido.service.domain.model.Restaurante;

public interface CriarRestauranteUseCase {
	 Restaurante criar(Restaurante restaurante);
}
