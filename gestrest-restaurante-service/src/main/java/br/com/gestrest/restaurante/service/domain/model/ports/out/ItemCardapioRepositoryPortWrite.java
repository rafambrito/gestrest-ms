package br.com.gestrest.restaurante.service.domain.model.ports.out;

import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;

public interface ItemCardapioRepositoryPortWrite {

    ItemCardapio salvar(ItemCardapio itemCardapio);

    void deletar(Long id);

    void deletarPorRestauranteId(Long restauranteId);
}
