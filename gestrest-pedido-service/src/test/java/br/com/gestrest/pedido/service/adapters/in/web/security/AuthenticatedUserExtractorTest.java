package br.com.gestrest.pedido.service.adapters.in.web.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.gestrest.pedido.service.adapters.out.persistence.UserDetailsImpl;

class AuthenticatedUserExtractorTest {

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldReturnAuthenticatedUserIdFromSecurityContext() {
        var userDetails = new UserDetailsImpl(42L, "teste", "CLIENTE");
        var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        var extractor = new AuthenticatedUserExtractor();
        assertEquals(42L, extractor.getAuthenticatedUserId());
    }

    @Test
    void shouldThrowWhenAuthenticationIsMissing() {
        SecurityContextHolder.clearContext();
        var extractor = new AuthenticatedUserExtractor();
        assertThrows(Exception.class, extractor::getAuthenticatedUserId);
    }
}
