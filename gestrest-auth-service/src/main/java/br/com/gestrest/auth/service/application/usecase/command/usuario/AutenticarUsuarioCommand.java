package br.com.gestrest.auth.service.application.usecase.command.usuario;

public record AutenticarUsuarioCommand(
        String loginOuEmail,
        String senha
) {
}