package br.com.gestrest.pedido.service.domain.model.ports.in.restaurante;

import java.util.List;

import br.com.gestrest.pedido.service.domain.model.Restaurante;

public interface ListarRestauranteUseCase {
	List<Restaurante> executar();
}
