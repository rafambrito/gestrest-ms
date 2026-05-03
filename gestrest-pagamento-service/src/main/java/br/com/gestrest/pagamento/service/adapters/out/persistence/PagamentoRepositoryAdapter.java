package br.com.gestrest.pagamento.service.adapters.out.persistence;

import org.springframework.stereotype.Component;

import br.com.gestrest.pagamento.service.adapters.out.persistence.mapper.PagamentoPersistenceMapper;
import br.com.gestrest.pagamento.service.adapters.out.persistence.repository.PagamentoJpaRepository;
import br.com.gestrest.pagamento.service.domain.model.Pagamento;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoRepositoryPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PagamentoRepositoryAdapter implements PagamentoRepositoryPort {

    private final PagamentoJpaRepository pagamentoJpaRepository;
    private final PagamentoPersistenceMapper pagamentoPersistenceMapper;

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        var entity = pagamentoPersistenceMapper.toEntity(pagamento);
        var savedEntity = pagamentoJpaRepository.save(entity);
        return pagamentoPersistenceMapper.toDomain(savedEntity);
    }
}

