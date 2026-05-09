package br.com.gestrest.restaurante.service.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import br.com.gestrest.restaurante.service.adapters.out.persistence.entity.ItemCardapioEntity;
import br.com.gestrest.restaurante.service.adapters.out.persistence.entity.RestauranteEntity;
import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;

@Component
public class ItemCardapioPersistenceMapper {

    public ItemCardapio toDomain(ItemCardapioEntity entity) {
        if (entity == null) {
            return null;
        }

        return ItemCardapio.existente(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.isAtivo(),
                entity.getRestaurante().getId());
    }

    public ItemCardapioEntity toEntity(ItemCardapio itemCardapio) {
        if (itemCardapio == null) {
            return null;
        }

        var entity = new ItemCardapioEntity();
        entity.setId(itemCardapio.getId());
        entity.setNome(itemCardapio.getNome());
        entity.setDescricao(itemCardapio.getDescricao());
        entity.setPreco(itemCardapio.getPreco());
        entity.setAtivo(itemCardapio.isAtivo());

        var restaurante = new RestauranteEntity();
        restaurante.setId(itemCardapio.getRestauranteId());
        entity.setRestaurante(restaurante);

        return entity;
    }
}
