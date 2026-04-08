package br.com.gestrest.auth.service.adapters.out.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestrest.auth.service.adapters.out.persistence.entity.UsuarioEntity;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findByLogin(String login);
}
