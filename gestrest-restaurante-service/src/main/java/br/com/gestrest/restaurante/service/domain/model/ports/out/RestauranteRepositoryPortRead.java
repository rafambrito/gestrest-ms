package br.com.gestrest.restaurante.service.domain.model.ports.out;

import java.util.List;
import java.util.Optional;

import br.com.gestrest.restaurante.service.domain.model.Restaurante;

public interface RestauranteRepositoryPortRead {

    Optional<Restaurante> buscarPorId(Long id);

    List<Restaurante> listar();
}
