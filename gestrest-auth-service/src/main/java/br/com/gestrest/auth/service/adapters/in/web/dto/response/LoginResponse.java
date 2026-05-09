package br.com.gestrest.auth.service.adapters.in.web.dto.response;

public record LoginResponse(
        String type,
        String token
) {
}