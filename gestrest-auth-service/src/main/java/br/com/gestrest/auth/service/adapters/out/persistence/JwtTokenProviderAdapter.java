package br.com.gestrest.auth.service.adapters.out.persistence;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.gestrest.auth.service.domain.model.Usuario;
import br.com.gestrest.auth.service.domain.model.ports.out.JwtTokenProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProviderAdapter implements JwtTokenProviderPort {

    @Value("${jwt.secret:gestrest-secret-key-for-jwt-tokens-very-long-and-secure}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private long expiration;

    @Override
    public String generateToken(Usuario usuario) {
        var now = new Date();
        var expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(usuario.getLogin())
                .claim("userId", usuario.getId())
                .claim("tipoUsuario", usuario.getTipoUsuario().getNome())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    @Override
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("userId", Long.class);
    }

    @Override
    public String getTipoUsuarioFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("tipoUsuario", String.class);
    }
}