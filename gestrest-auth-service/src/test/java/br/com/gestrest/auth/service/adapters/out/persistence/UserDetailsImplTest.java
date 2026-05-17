package br.com.gestrest.auth.service.adapters.out.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserDetailsImplTest {

    @Test
    void shouldMapDonoRestauranteToAdminRole() {
        var userDetails = new UserDetailsImpl(1L, "dono", "ADMIN");

        assertEquals(1, userDetails.getAuthorities().size());
        GrantedAuthority authority = userDetails.getAuthorities().iterator().next();
        assertEquals("ROLE_ADMIN", authority.getAuthority());
    }

    @Test
    void shouldMapClienteToClienteRole() {
        var userDetails = new UserDetailsImpl(2L, "cliente", "CLIENTE");

        assertEquals(1, userDetails.getAuthorities().size());
        GrantedAuthority authority = userDetails.getAuthorities().iterator().next();
        assertEquals("ROLE_CLIENTE", authority.getAuthority());
    }
}
