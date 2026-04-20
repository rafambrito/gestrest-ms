package br.com.gestrest.pedido.service.adapters.out.integration;

import org.springframework.stereotype.Component;

import br.com.gestrest.pedido.service.domain.model.ports.out.DonoRestauranteValidatorPort;

@Component
public class AuthDonoRestauranteValidatorAdapter implements DonoRestauranteValidatorPort {

    @Override
    public void validar(Long donoId) {
        if (donoId == null) {
            throw new IllegalArgumentException("Dono é obrigatório");
        }
    }
}
