package br.com.gestrest.pagamento.service.adapters.out.persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.gestrest.pagamento.service.domain.model.ports.out.JwtTokenProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProviderAdapter implements JwtTokenProviderPort {

    @Value("${jwt.secret:gestrest-secret-key-for-jwt-tokens-very-long-and-secure}")
    private String secret;

    @Override
    public String getUsernameFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    @Override
    public Long getUserIdFromToken(String token) {
        var claim = parseClaims(token).get("userId");
        return claim instanceof Number ? ((Number) claim).longValue() : null;
    }

    @Override
    public String getTipoUsuarioFromToken(String token) {
        return parseClaims(token).get("tipoUsuario", String.class);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
