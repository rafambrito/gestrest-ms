package br.com.gestrest.auth.service.adapters.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.gestrest.auth.service.adapters.out.persistence.entity.TipoUsuarioEntity;
import br.com.gestrest.auth.service.adapters.out.persistence.entity.UsuarioEntity;
import br.com.gestrest.auth.service.adapters.out.persistence.mapper.UsuarioPersistenceMapper;
import br.com.gestrest.auth.service.adapters.out.persistence.repository.UsuarioJpaRepository;
import br.com.gestrest.auth.service.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.gestrest.auth.service.domain.model.Usuario;
import br.com.gestrest.auth.service.domain.model.ports.out.TipoUsuarioRepositoryPort;
import br.com.gestrest.auth.service.domain.model.ports.out.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository repository;
    private final TipoUsuarioRepositoryPort tipoRepository;
    private final UsuarioPersistenceMapper mapper;

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNome(usuario.getNome());
        entity.setEmail(usuario.getEmail());
        entity.setLogin(usuario.getLogin());
        entity.setSenha(usuario.getSenha());
        entity.setDataUltimaAlteracao(usuario.getDataUltimaAlteracao());

        var tipo = tipoRepository.buscarPorId(usuario.getTipoUsuario().getId())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(usuario.getTipoUsuario().getId()));

        var tipoEntity = new TipoUsuarioEntity();
        tipoEntity.setId(tipo.getId());
        tipoEntity.setNome(tipo.getNome());

        entity.setTipoUsuario(tipoEntity);

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
        return repository.findByLogin(login).map(mapper::toDomain);
    }
}
