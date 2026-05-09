package br.com.gestrest.auth.service.domain.model.ports.out;

import java.util.Optional;

import br.com.gestrest.auth.service.domain.model.TipoUsuario;

public interface TipoUsuarioRepositoryPort {

    Optional<TipoUsuario> buscarPorId(Long id);
}
