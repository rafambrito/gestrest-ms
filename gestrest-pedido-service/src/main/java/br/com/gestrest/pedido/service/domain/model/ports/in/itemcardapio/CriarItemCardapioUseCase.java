package br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio;

import br.com.gestrest.pedido.service.domain.model.ItemCardapio;

public interface CriarItemCardapioUseCase {
	ItemCardapio criar(ItemCardapio item);
}
