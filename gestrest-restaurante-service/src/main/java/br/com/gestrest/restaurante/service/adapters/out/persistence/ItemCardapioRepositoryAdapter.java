package br.com.gestrest.restaurante.service.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.gestrest.restaurante.service.adapters.out.persistence.mapper.ItemCardapioPersistenceMapper;
import br.com.gestrest.restaurante.service.adapters.out.persistence.repository.ItemCardapioJpaRepository;
import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortRead;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortWrite;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ItemCardapioRepositoryAdapter implements ItemCardapioRepositoryPortRead, ItemCardapioRepositoryPortWrite {

    private final ItemCardapioJpaRepository repository;
    private final ItemCardapioPersistenceMapper mapper;

    @Override
    public Optional<ItemCardapio> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<ItemCardapio> listarPorRestauranteId(Long restauranteId) {
        return repository.findByRestauranteId(restauranteId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public ItemCardapio salvar(ItemCardapio itemCardapio) {
        var entity = mapper.toEntity(itemCardapio);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deletarPorRestauranteId(Long restauranteId) {
        repository.deleteByRestauranteId(restauranteId);
    }
}
