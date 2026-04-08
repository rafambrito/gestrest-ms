package br.com.gestrest.auth.service.adapters.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.gestrest.auth.service.adapters.out.persistence.mapper.TipoUsuarioPersistenceMapper;
import br.com.gestrest.auth.service.adapters.out.persistence.repository.TipoUsuarioJpaRepository;
import br.com.gestrest.auth.service.domain.model.TipoUsuario;
import br.com.gestrest.auth.service.domain.model.ports.out.TipoUsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TipoUsuarioRepositoryAdapter implements TipoUsuarioRepositoryPort {

    private final TipoUsuarioJpaRepository repository;
    private final TipoUsuarioPersistenceMapper mapper;

    @Override
    public Optional<TipoUsuario> buscarPorId(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
