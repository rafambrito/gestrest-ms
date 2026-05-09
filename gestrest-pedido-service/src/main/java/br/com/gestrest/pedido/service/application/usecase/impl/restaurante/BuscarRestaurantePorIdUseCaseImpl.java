package br.com.gestrest.pedido.service.application.usecase.impl.restaurante;


import br.com.gestrest.pedido.service.domain.exception.RestauranteNaoEncontradoException;
import br.com.gestrest.pedido.service.domain.model.Restaurante;
import br.com.gestrest.pedido.service.domain.model.ports.in.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.RestauranteRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarRestaurantePorIdUseCaseImpl implements BuscarRestaurantePorIdUseCase {

    private final RestauranteRepositoryPort repository;

    @Override
    public Restaurante executar(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }
}