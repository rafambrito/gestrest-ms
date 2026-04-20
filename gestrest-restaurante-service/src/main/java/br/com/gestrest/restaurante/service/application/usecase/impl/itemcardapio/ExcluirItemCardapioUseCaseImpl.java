package br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio;

import br.com.gestrest.restaurante.service.domain.exception.EntityNotFoundException;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.ExcluirItemCardapioUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortRead;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortWrite;

public class ExcluirItemCardapioUseCaseImpl implements ExcluirItemCardapioUseCase {

    private final ItemCardapioRepositoryPortRead itemCardapioRepositoryRead;
    private final ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite;

    public ExcluirItemCardapioUseCaseImpl(
            ItemCardapioRepositoryPortRead itemCardapioRepositoryRead,
            ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite) {
        this.itemCardapioRepositoryRead = itemCardapioRepositoryRead;
        this.itemCardapioRepositoryWrite = itemCardapioRepositoryWrite;
    }

    @Override
    public void excluir(Long id) {
        if (itemCardapioRepositoryRead.buscarPorId(id).isEmpty()) {
            throw new EntityNotFoundException(id, "ItemCardapio");
        }

        itemCardapioRepositoryWrite.deletar(id);
    }
}
