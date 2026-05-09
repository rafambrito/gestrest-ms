package br.com.gestrest.restaurante.service.domain.model.ports.out;

import br.com.gestrest.restaurante.service.domain.model.Restaurante;

public interface RestauranteRepositoryPortWrite {

    Restaurante salvar(Restaurante restaurante);

    void deletar(Long id);
}
