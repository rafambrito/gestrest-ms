package br.com.gestrest.api.adapters.out.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.gestrest.pedido.service.adapter.out.persistence.entity.RestauranteEntity;
import br.com.gestrest.pedido.service.adapter.out.persistence.mapper.RestaurantePersistenceMapper;
import br.com.gestrest.pedido.service.domain.model.Restaurante;

class RestaurantePersistenceMapperTest {

    private final RestaurantePersistenceMapper mapper = new RestaurantePersistenceMapper();

    @Test
    void toEntityAndBack() {
        var domain = Restaurante.existente(3L, "João da Silva", "Rua das Rosas, São Paulo/SP", "Francesa", "Ter-Dom 12:00-23:00", 4L);
        RestauranteEntity entity = mapper.toEntity(domain);

        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getNome(), entity.getNome());

        var back = mapper.toDomain(entity);
        assertEquals(entity.getNome(), back.getNome());
        assertEquals(entity.getDonoId(), back.getDonoId());
    }
}
