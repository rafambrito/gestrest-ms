package br.com.gestrest.api.adapters.out.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.com.gestrest.pedido.service.adapters.out.persistence.entity.RestauranteEntity;
import br.com.gestrest.pedido.service.adapters.out.persistence.mapper.RestaurantePersistenceMapper;
import br.com.gestrest.pedido.service.domain.model.Restaurante;

class RestaurantePersistenceMapperTest {

    private final RestaurantePersistenceMapper mapper = new RestaurantePersistenceMapper();

    @Test
    void deveMapearDomainParaEntityEVoltaCorretamente() {
        var domain = Restaurante.existente(3L, "João da Silva", "Rua das Rosas, São Paulo/SP", "Francesa", "Ter-Dom 12:00-23:00", 4L);

        // Testa comportamento: conversão domain -> entity preserva todos os dados
        RestauranteEntity entity = mapper.toEntity(domain);
        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getNome(), entity.getNome());
        assertEquals(domain.getEndereco(), entity.getEndereco());
        assertEquals(domain.getTipoCozinha(), entity.getTipoCozinha());
        assertEquals(domain.getHorarioFuncionamento(), entity.getHorarioFuncionamento());
        assertEquals(domain.getDonoId(), entity.getDonoId());

        // Testa comportamento: conversão entity -> domain preserva todos os dados
        var backToDomain = mapper.toDomain(entity);
        assertNotNull(backToDomain);
        assertEquals(entity.getId(), backToDomain.getId());
        assertEquals(entity.getNome(), backToDomain.getNome());
        assertEquals(entity.getEndereco(), backToDomain.getEndereco());
        assertEquals(entity.getTipoCozinha(), backToDomain.getTipoCozinha());
        assertEquals(entity.getHorarioFuncionamento(), backToDomain.getHorarioFuncionamento());
        assertEquals(entity.getDonoId(), backToDomain.getDonoId());
    }
}
