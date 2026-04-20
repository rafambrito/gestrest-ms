package br.com.gestrest.pedido.service.application.usecase.impl.itemcardapio;


import br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio.ExcluirItemCardapioUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.ItemCardapioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExcluirItemCardapioUseCaseImpl implements ExcluirItemCardapioUseCase {

    private final ItemCardapioRepositoryPort repository;

    @Override
    public void deletar(Long id) {
        repository.deletar(id);
    }
}

