package br.com.gestrest.pedido.service.application.usecase.impl.restaurante;


import br.com.gestrest.pedido.service.domain.exception.RestauranteNaoEncontradoException;
import br.com.gestrest.pedido.service.domain.model.Restaurante;
import br.com.gestrest.pedido.service.domain.model.ports.in.restaurante.AtualizarRestauranteUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.RestauranteRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarRestauranteUseCaseImpl implements AtualizarRestauranteUseCase {

    private final RestauranteRepositoryPort repository;
    private final ValidarDonoRestauranteService validarDonoRestauranteService;

    @Override
    public Restaurante atualizar(Restaurante restaurante) {

        var existente = repository.buscarPorId(restaurante.getId())
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restaurante.getId()));

        validarDonoRestauranteService.validar(restaurante.getDonoId());

        existente.atualizar(
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento()
        );

        return repository.salvar(existente);
    }
}