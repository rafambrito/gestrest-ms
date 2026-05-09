package br.com.gestrest.pedido.service.application.usecase.impl.restaurante;


import br.com.gestrest.pedido.service.domain.model.Restaurante;
import br.com.gestrest.pedido.service.domain.model.ports.in.restaurante.CriarRestauranteUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.RestauranteRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CriarRestauranteUseCaseImpl implements CriarRestauranteUseCase {

    private final RestauranteRepositoryPort repository;
    private final ValidarDonoRestauranteService validarDonoRestauranteService;

    @Override
    public Restaurante criar(Restaurante restaurante) {
        validarDonoRestauranteService.validar(restaurante.getDonoId());

        return repository.salvar(restaurante);
    }
}