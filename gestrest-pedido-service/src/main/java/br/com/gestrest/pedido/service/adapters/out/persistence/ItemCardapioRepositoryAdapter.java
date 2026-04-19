package br.com.gestrest.pedido.service.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.gestrest.pedido.service.adapters.out.persistence.mapper.ItemCardapioPersistenceMapper;
import br.com.gestrest.pedido.service.adapters.out.persistence.repository.ItemCardapioJpaRepository;
import br.com.gestrest.pedido.service.domain.model.ItemCardapio;
import br.com.gestrest.pedido.service.domain.model.ports.out.ItemCardapioRepositoryPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ItemCardapioRepositoryAdapter implements ItemCardapioRepositoryPort {

	private final ItemCardapioJpaRepository repository;
	private final ItemCardapioPersistenceMapper mapper;

	@Override
	public ItemCardapio salvar(ItemCardapio item) {
		var entity = mapper.toEntity(item);
		entity = repository.save(entity);
		return mapper.toDomain(entity);
	}

	@Override
	public Optional<ItemCardapio> buscarPorId(Long id) {
		return repository.findById(id).map(mapper::toDomain);
	}

	@Override
	public List<ItemCardapio> listarPorRestauranteId(Long restauranteId) {
		return repository.findByRestauranteId(restauranteId).stream().map(mapper::toDomain).toList();
	}

	@Override
	public void deletar(Long id) {
		repository.deleteById(id);
	}
}
