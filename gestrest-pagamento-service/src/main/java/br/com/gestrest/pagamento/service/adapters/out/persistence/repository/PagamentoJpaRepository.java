package br.com.gestrest.pagamento.service.adapters.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestrest.pagamento.service.adapters.out.persistence.entity.PagamentoEntity;

public interface PagamentoJpaRepository extends JpaRepository<PagamentoEntity, Long> {
}
