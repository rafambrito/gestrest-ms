package br.com.gestrest.pedido.service.application.usecase.impl.restaurante;


import br.com.gestrest.pedido.service.domain.exception.RecursoEmUsoException;
import br.com.gestrest.pedido.service.domain.model.ports.in.restaurante.ExcluirRestauranteUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.ItemCardapioRepositoryPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.RestauranteRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExcluirRestauranteUseCaseImpl implements ExcluirRestauranteUseCase {

    private final RestauranteRepositoryPort repository;
    private final ItemCardapioRepositoryPort itemRepository;

    @Override
    public void deletar(Long id) {
        var itens = itemRepository.listarPorRestauranteId(id);
        if (itens != null && !itens.isEmpty()) {
            throw new RecursoEmUsoException("O restaurante possui itens de cardápio e não pode ser excluído");
        }

        repository.deletar(id);
    }
}