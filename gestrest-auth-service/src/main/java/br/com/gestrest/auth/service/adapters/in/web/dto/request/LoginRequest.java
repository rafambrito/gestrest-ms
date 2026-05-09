package br.com.gestrest.auth.service.adapters.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Login ou email é obrigatório") String loginOuEmail,
        @NotBlank(message = "Senha é obrigatória") String senha
) {
}