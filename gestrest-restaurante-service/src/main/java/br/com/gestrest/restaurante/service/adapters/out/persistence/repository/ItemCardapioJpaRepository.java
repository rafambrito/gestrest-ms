package br.com.gestrest.restaurante.service.adapters.out.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestrest.restaurante.service.adapters.out.persistence.entity.ItemCardapioEntity;

public interface ItemCardapioJpaRepository extends JpaRepository<ItemCardapioEntity, Long> {

    List<ItemCardapioEntity> findByRestauranteId(Long restauranteId);

    void deleteByRestauranteId(Long restauranteId);
}
