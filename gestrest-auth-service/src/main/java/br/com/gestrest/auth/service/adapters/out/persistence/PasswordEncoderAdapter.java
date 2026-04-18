package br.com.gestrest.auth.service.adapters.out.persistence;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.gestrest.auth.service.domain.model.ports.out.PasswordEncoderPort;

@Component
public class PasswordEncoderAdapter implements PasswordEncoderPort {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String rawPassword) {
        if (rawPassword == null) {
            return null;
        }
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        return encoder.matches(rawPassword, encodedPassword);
    }
}
