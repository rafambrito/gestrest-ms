package br.com.gestrest.pedido.service.application.usecase.impl.itemcardapio;


import br.com.gestrest.pedido.service.domain.exception.RestauranteNaoEncontradoException;
import br.com.gestrest.pedido.service.domain.model.ItemCardapio;
import br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio.CriarItemCardapioUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.ItemCardapioRepositoryPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.RestauranteRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CriarItemCardapioUseCaseImpl implements CriarItemCardapioUseCase {

    private final ItemCardapioRepositoryPort repository;
    private final RestauranteRepositoryPort restauranteRepository;

    @Override
    public ItemCardapio criar(ItemCardapio item) {
        var restauranteId = item.getRestauranteId();
        restauranteRepository.buscarPorId(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));

        return repository.salvar(item);
    }
}