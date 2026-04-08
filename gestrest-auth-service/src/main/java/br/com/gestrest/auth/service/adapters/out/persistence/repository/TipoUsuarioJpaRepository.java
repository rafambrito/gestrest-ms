package br.com.gestrest.auth.service.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestrest.auth.service.adapters.out.persistence.entity.TipoUsuarioEntity;

@Repository
public interface TipoUsuarioJpaRepository extends JpaRepository<TipoUsuarioEntity, Long> {
}
