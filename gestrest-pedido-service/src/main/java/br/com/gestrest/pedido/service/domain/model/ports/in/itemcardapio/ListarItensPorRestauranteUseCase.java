package br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio;

import java.util.List;

import br.com.gestrest.pedido.service.domain.model.ItemCardapio;

public interface ListarItensPorRestauranteUseCase {
	List<ItemCardapio> listarPorRestauranteId(Long restauranteId);
}
