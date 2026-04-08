package br.com.gestrest.auth.service.application.usecase.command.usuario;

public record CriarUsuarioCommand(
        String nome,
        String email,
        String login,
        String senha,
        Long tipoUsuarioId
) {
}
