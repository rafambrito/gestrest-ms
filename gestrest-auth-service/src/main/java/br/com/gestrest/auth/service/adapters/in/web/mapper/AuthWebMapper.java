package br.com.gestrest.auth.service.adapters.in.web.mapper;

import org.springframework.stereotype.Component;

import br.com.gestrest.auth.service.adapters.in.web.dto.request.LoginRequest;
import br.com.gestrest.auth.service.application.usecase.command.usuario.AutenticarUsuarioCommand;

@Component
public class AuthWebMapper {

    public AutenticarUsuarioCommand toDomain(LoginRequest request) {
        if (request == null) {
            return null;
        }

        return new AutenticarUsuarioCommand(
                request.loginOuEmail() != null ? request.loginOuEmail().trim().toLowerCase() : null,
                request.senha()
        );
    }
}