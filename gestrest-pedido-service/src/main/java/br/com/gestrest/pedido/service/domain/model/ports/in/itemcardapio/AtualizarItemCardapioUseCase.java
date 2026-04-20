package br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio;

import br.com.gestrest.pedido.service.domain.model.ItemCardapio;

public interface AtualizarItemCardapioUseCase {
    ItemCardapio atualizar(ItemCardapio item);
}
