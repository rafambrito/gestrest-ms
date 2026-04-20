package br.com.gestrest.pedido.service.application.usecase.impl.itemcardapio;

import java.util.List;


import br.com.gestrest.pedido.service.domain.model.ItemCardapio;
import br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio.ListarItensPorRestauranteUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.ItemCardapioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListarItensPorRestauranteUseCaseImpl implements ListarItensPorRestauranteUseCase {

    private final ItemCardapioRepositoryPort repository;

    @Override
    public List<ItemCardapio> listarPorRestauranteId(Long restauranteId) {
        return repository.listarPorRestauranteId(restauranteId);
    }
}
