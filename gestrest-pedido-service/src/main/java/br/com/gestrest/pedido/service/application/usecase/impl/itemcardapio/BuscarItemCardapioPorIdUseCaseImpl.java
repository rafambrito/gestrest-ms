package br.com.gestrest.pedido.service.application.usecase.impl.itemcardapio;


import br.com.gestrest.pedido.service.domain.exception.EntityNotFoundException;
import br.com.gestrest.pedido.service.domain.model.ItemCardapio;
import br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio.BuscarItemCardapioPorIdUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.ItemCardapioRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarItemCardapioPorIdUseCaseImpl implements BuscarItemCardapioPorIdUseCase {

	private final ItemCardapioRepositoryPort repository;

	@Override
	public ItemCardapio buscarPorId(Long id) {
		return repository.buscarPorId(id).orElseThrow(() -> new EntityNotFoundException(id, "Item"));
	}
}