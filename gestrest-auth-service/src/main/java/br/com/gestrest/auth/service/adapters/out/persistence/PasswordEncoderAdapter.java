package br.com.gestrest.auth.service.adapters.out.persistence;

import org.springframework.stereotype.Component;

import br.com.gestrest.auth.service.domain.model.ports.out.PasswordEncoderPort;

@Component
public class PasswordEncoderAdapter implements PasswordEncoderPort {

    @Override
    public String encode(String rawPassword) {
        if (rawPassword == null) {
            return null;
        }
        return "enc(" + rawPassword + ")";
    }
}
