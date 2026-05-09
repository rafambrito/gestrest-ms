package br.com.gestrest.restaurante.service.adapters.out.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private final Long userId;
    private final String username;
    private final String tipoUsuario;

    public UserDetailsImpl(Long userId, String username, String tipoUsuario) {
        this.userId = userId;
        this.username = username;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(mapTipoUsuarioToRole(tipoUsuario)));
    }

    private String mapTipoUsuarioToRole(String tipoUsuario) {
        if ("DONO_RESTAURANTE".equals(tipoUsuario) || "DONO".equals(tipoUsuario)) {
            return "ROLE_DONO";
        }
        if ("CLIENTE".equals(tipoUsuario)) {
            return "ROLE_CLIENTE";
        }
        return "ROLE_USER";
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getUserId() {
        return userId;
    }
}
