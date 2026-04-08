package br.com.gestrest.auth.service.adapters.in.web.dto.response;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String login,
        TipoUsuarioResponse tipoUsuario,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaAlteracao
) {
}
