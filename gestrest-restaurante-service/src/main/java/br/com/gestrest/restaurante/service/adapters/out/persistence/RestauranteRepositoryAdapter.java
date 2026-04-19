package br.com.gestrest.restaurante.service.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.gestrest.restaurante.service.adapters.out.persistence.mapper.RestaurantePersistenceMapper;
import br.com.gestrest.restaurante.service.adapters.out.persistence.repository.RestauranteJpaRepository;
import br.com.gestrest.restaurante.service.domain.model.Restaurante;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortRead;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortWrite;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestauranteRepositoryAdapter implements RestauranteRepositoryPortRead, RestauranteRepositoryPortWrite {

    private final RestauranteJpaRepository repository;
    private final RestaurantePersistenceMapper mapper;

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Restaurante> listar() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        var entity = mapper.toEntity(restaurante);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
