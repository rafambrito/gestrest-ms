package br.com.gestrest.pedido.service.adapters.out.persistence.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gestrest.pedido.service.adapters.out.persistence.entity.ItemPedidoEntity;
import br.com.gestrest.pedido.service.adapters.out.persistence.entity.PedidoEntity;
import br.com.gestrest.pedido.service.domain.model.ItemPedido;
import br.com.gestrest.pedido.service.domain.model.Pedido;

@Component
public class PedidoPersistenceMapper {

    public Pedido toDomain(PedidoEntity entity) {
        var itens = entity.getItens().stream()
                .map(i -> ItemPedido.criar(i.getItemCardapioId(), i.getNomeItem(), i.getQuantidade(), i.getPrecoUnitario()))
                .toList();

        return Pedido.existente(
                entity.getId(),
                entity.getUsuarioId(),
                entity.getRestauranteId(),
                itens,
                entity.getValorTotal(),
                entity.getStatus(),
                entity.getDataCriacao(),
                entity.getDataUltimaAlteracao());
    }

    public PedidoEntity toEntity(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        entity.setId(pedido.getId());
        entity.setUsuarioId(pedido.getUsuarioId());
        entity.setRestauranteId(pedido.getRestauranteId());
        entity.setValorTotal(pedido.getValorTotal());
        entity.setStatus(pedido.getStatus());

        List<ItemPedidoEntity> itens = new ArrayList<>();
        for (var item : pedido.getItens()) {
            ItemPedidoEntity itemEntity = new ItemPedidoEntity();
            itemEntity.setPedido(entity);
            itemEntity.setItemCardapioId(item.getItemCardapioId());
            itemEntity.setNomeItem(item.getNomeItem());
            itemEntity.setQuantidade(item.getQuantidade());
            itemEntity.setPrecoUnitario(item.getPrecoUnitario());
            itens.add(itemEntity);
        }
        entity.setItens(itens);
        return entity;
    }
}
