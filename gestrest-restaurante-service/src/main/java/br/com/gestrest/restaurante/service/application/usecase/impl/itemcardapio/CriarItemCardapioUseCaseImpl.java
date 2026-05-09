package br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio;

import br.com.gestrest.restaurante.service.application.usecase.command.itemcardapio.CriarItemCardapioCommand;
import br.com.gestrest.restaurante.service.domain.exception.RestauranteNaoEncontradoException;
import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.CriarItemCardapioUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortWrite;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortRead;

public class CriarItemCardapioUseCaseImpl implements CriarItemCardapioUseCase {

    private final ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite;
    private final RestauranteRepositoryPortRead restauranteRepositoryRead;

    public CriarItemCardapioUseCaseImpl(
            ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite,
            RestauranteRepositoryPortRead restauranteRepositoryRead) {
        this.itemCardapioRepositoryWrite = itemCardapioRepositoryWrite;
        this.restauranteRepositoryRead = restauranteRepositoryRead;
    }

    @Override
    public ItemCardapio criar(CriarItemCardapioCommand command) {
        var restauranteId = command.restauranteId();

        if (restauranteRepositoryRead.buscarPorId(restauranteId).isEmpty()) {
            throw new RestauranteNaoEncontradoException(restauranteId);
        }

        var item = ItemCardapio.criar(
                command.nome(),
                command.descricao(),
                command.preco(),
                restauranteId);

        return itemCardapioRepositoryWrite.salvar(item);
    }
}
