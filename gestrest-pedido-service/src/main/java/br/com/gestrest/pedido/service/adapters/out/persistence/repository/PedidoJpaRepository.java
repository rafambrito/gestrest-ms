package br.com.gestrest.pedido.service.adapters.out.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestrest.pedido.service.adapters.out.persistence.entity.PedidoEntity;

public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, Long> {
    List<PedidoEntity> findByUsuarioIdOrderByIdDesc(Long usuarioId);
}
