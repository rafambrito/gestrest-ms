package br.com.gestrest.auth.service.domain.model.ports.out;

import br.com.gestrest.auth.service.domain.model.Usuario;

public interface JwtTokenProviderPort {

    String generateToken(Usuario usuario);

    boolean validateToken(String token);

    String getUsernameFromToken(String token);
}