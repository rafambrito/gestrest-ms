package br.com.gestrest.pedido.service.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestrest.pedido.service.adapters.out.persistence.entity.RestauranteEntity;

public interface RestauranteJpaRepository extends JpaRepository<RestauranteEntity, Long> {
	
}
