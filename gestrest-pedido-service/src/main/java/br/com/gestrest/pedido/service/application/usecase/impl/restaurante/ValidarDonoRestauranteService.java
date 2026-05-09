package br.com.gestrest.pedido.service.application.usecase.impl.restaurante;


import br.com.gestrest.pedido.service.domain.model.ports.out.DonoRestauranteValidatorPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidarDonoRestauranteService {

    private final DonoRestauranteValidatorPort donoRestauranteValidatorPort;

    public void validar(Long donoId) {
        donoRestauranteValidatorPort.validar(donoId);
    }
}