package br.com.gestrest.pagamento.service.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import br.com.gestrest.pagamento.service.adapters.out.persistence.entity.PagamentoEntity;
import br.com.gestrest.pagamento.service.domain.model.Pagamento;

@Component
public class PagamentoPersistenceMapper {

    public PagamentoEntity toEntity(Pagamento pagamento) {
        var entity = new PagamentoEntity();
        entity.setId(pagamento.getId());
        entity.setPedidoId(pagamento.getPedidoId());
        entity.setUsuarioId(pagamento.getUsuarioId());
        entity.setValor(pagamento.getValor());
        entity.setStatus(pagamento.getStatus());
        entity.setPagamentoIdExterno(pagamento.getPagamentoIdExterno());
        entity.setDataCriacao(pagamento.getDataCriacao());
        return entity;
    }

    public Pagamento toDomain(PagamentoEntity entity) {
        return Pagamento.existente(
            entity.getId(),
            entity.getPedidoId(),
            entity.getUsuarioId(),
            entity.getValor(),
            entity.getStatus(),
            entity.getPagamentoIdExterno(),
            entity.getDataCriacao()
        );
    }
}
