package br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio;

import br.com.gestrest.restaurante.service.application.usecase.command.itemcardapio.AtualizarItemCardapioCommand;
import br.com.gestrest.restaurante.service.domain.exception.EntityNotFoundException;
import br.com.gestrest.restaurante.service.domain.exception.RestauranteNaoEncontradoException;
import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.AtualizarItemCardapioUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortRead;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortWrite;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortRead;

public class AtualizarItemCardapioUseCaseImpl implements AtualizarItemCardapioUseCase {

    private final ItemCardapioRepositoryPortRead itemCardapioRepositoryRead;
    private final ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite;
    private final RestauranteRepositoryPortRead restauranteRepositoryRead;

    public AtualizarItemCardapioUseCaseImpl(
            ItemCardapioRepositoryPortRead itemCardapioRepositoryRead,
            ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite,
            RestauranteRepositoryPortRead restauranteRepositoryRead) {
        this.itemCardapioRepositoryRead = itemCardapioRepositoryRead;
        this.itemCardapioRepositoryWrite = itemCardapioRepositoryWrite;
        this.restauranteRepositoryRead = restauranteRepositoryRead;
    }

    @Override
    public ItemCardapio atualizar(Long id, AtualizarItemCardapioCommand command) {
        var item = itemCardapioRepositoryRead.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "ItemCardapio"));

        var restauranteId = command.restauranteId();
        if (restauranteRepositoryRead.buscarPorId(restauranteId).isEmpty()) {
            throw new RestauranteNaoEncontradoException(restauranteId);
        }

        item.atualizar(command.nome(), command.descricao(), command.preco(), command.ativo(), restauranteId);
        return itemCardapioRepositoryWrite.salvar(item);
    }
}
