package br.com.gestrest.restaurante.service.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import br.com.gestrest.restaurante.service.adapters.out.persistence.entity.RestauranteEntity;
import br.com.gestrest.restaurante.service.domain.model.Restaurante;

@Component
public class RestaurantePersistenceMapper {

    public Restaurante toDomain(RestauranteEntity entity) {
        if (entity == null) {
            return null;
        }

        return Restaurante.existente(entity.getId(), entity.getNome(), entity.isAtivo());
    }

    public RestauranteEntity toEntity(Restaurante restaurante) {
        if (restaurante == null) {
            return null;
        }

        var entity = new RestauranteEntity();
        entity.setId(restaurante.getId());
        entity.setNome(restaurante.getNome());
        entity.setAtivo(restaurante.isAtivo());
        return entity;
    }
}
