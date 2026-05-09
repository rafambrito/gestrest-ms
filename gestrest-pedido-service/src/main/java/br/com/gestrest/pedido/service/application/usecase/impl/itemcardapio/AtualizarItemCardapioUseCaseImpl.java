package br.com.gestrest.pedido.service.application.usecase.impl.itemcardapio;


import br.com.gestrest.pedido.service.domain.exception.EntityNotFoundException;
import br.com.gestrest.pedido.service.domain.model.ItemCardapio;
import br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio.AtualizarItemCardapioUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.ItemCardapioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarItemCardapioUseCaseImpl implements AtualizarItemCardapioUseCase {

	private final ItemCardapioRepositoryPort repository;

	@Override
	public ItemCardapio atualizar(ItemCardapio item) {

		var existente = repository.buscarPorId(item.getId())
				.orElseThrow(() -> new EntityNotFoundException(item.getId(), "Item"));

		existente.atualizar(
				item.getNome(),
				item.getDescricao(),
				item.getPreco(),
				item.isDisponivelSomenteNoLocal(),
				item.getFotoPath());

		return repository.salvar(existente);
	}
}