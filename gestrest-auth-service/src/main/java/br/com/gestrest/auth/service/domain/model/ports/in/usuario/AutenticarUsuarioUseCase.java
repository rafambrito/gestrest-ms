package br.com.gestrest.auth.service.domain.model.ports.in.usuario;

import br.com.gestrest.auth.service.application.usecase.command.usuario.AutenticarUsuarioCommand;

public interface AutenticarUsuarioUseCase {
    String autenticar(AutenticarUsuarioCommand command);
}