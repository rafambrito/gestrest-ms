package br.com.gestrest.pedido.service.application.usecase.impl.restaurante;

import java.util.List;


import br.com.gestrest.pedido.service.domain.model.Restaurante;
import br.com.gestrest.pedido.service.domain.model.ports.in.restaurante.ListarRestauranteUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.RestauranteRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListarRestauranteUseCaseImpl implements ListarRestauranteUseCase {

    private final RestauranteRepositoryPort repository;

    @Override
    public List<Restaurante> executar() {
        return repository.listar();
    }
}

