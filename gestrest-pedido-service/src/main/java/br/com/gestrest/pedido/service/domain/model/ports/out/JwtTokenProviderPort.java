package br.com.gestrest.pedido.service.domain.model.ports.out;

public interface JwtTokenProviderPort {

    boolean validateToken(String token);

    String getUsernameFromToken(String token);

    Long getUserIdFromToken(String token);

    String getTipoUsuarioFromToken(String token);
}
