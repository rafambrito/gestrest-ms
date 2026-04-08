package br.com.gestrest.auth.service.domain.model.ports.out;

import br.com.gestrest.auth.service.domain.model.Usuario;

public interface UsuarioRepositoryPortWrite {

    Usuario salvar(Usuario usuario);
}
