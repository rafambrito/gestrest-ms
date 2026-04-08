package br.com.gestrest.auth.service.domain.model.ports.in.usuario;

import br.com.gestrest.auth.service.application.usecase.command.usuario.CriarUsuarioCommand;
import br.com.gestrest.auth.service.domain.model.Usuario;

public interface CriarUsuarioUseCase {
    Usuario criar(CriarUsuarioCommand command);
}
