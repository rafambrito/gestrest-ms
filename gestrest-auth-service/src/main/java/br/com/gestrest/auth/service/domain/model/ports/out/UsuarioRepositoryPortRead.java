package br.com.gestrest.auth.service.domain.model.ports.out;

import java.util.Optional;

import br.com.gestrest.auth.service.domain.model.Usuario;

public interface UsuarioRepositoryPortRead {

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorLogin(String login);
}
