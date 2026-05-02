package br.com.gestrest.api.adapters.out.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import br.com.gestrest.pedido.service.adapters.out.persistence.entity.ItemCardapioEntity;
import br.com.gestrest.pedido.service.adapters.out.persistence.mapper.ItemCardapioPersistenceMapper;
import br.com.gestrest.pedido.service.domain.model.ItemCardapio;

class ItemCardapioPersistenceMapperTest {

    private final ItemCardapioPersistenceMapper mapper = new ItemCardapioPersistenceMapper();

    @Test
    void deveMapearDomainParaEntityEVoltaCorretamente() {
        var domain = ItemCardapio.existente(2L, "Ravioli de Carne", "Massa fresca recheada com carne e molho de tomate", BigDecimal.valueOf(42.5),
            5L, true, "/itens/ravioli.jpg");

        // Testa comportamento: conversão domain -> entity preserva dados
        var entity = mapper.toEntity(domain);
        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getNome(), entity.getNome());
        assertEquals(domain.getDescricao(), entity.getDescricao());
        assertEquals(0, domain.getPreco().compareTo(entity.getPreco()));
        assertEquals(domain.getRestauranteId(), entity.getRestauranteId());
        assertEquals(domain.isDisponivelSomenteNoLocal(), entity.getDisponivelSomenteNoLocal());
        assertEquals(domain.getFotoPath(), entity.getFotoPath());

        // Testa comportamento: conversão entity -> domain preserva dados
        var backToDomain = mapper.toDomain(entity);
        assertNotNull(backToDomain);
        assertEquals(entity.getId(), backToDomain.getId());
        assertEquals(entity.getNome(), backToDomain.getNome());
        assertEquals(entity.getDescricao(), backToDomain.getDescricao());
        assertEquals(0, entity.getPreco().compareTo(backToDomain.getPreco()));
        assertEquals(entity.getRestauranteId(), backToDomain.getRestauranteId());
        assertEquals(entity.getDisponivelSomenteNoLocal(), backToDomain.isDisponivelSomenteNoLocal());
        assertEquals(entity.getFotoPath(), backToDomain.getFotoPath());
    }

    @Test
    void deveMapearEntityParaDomainCorretamente() {
        var entity = new ItemCardapioEntity(3L, "Moqueca de Peixe", "Acompanha arroz branco e pirão", BigDecimal.valueOf(57.5), 8L,
            false, "/itens/moqueca.jpg", LocalDateTime.now());

        // Testa comportamento: conversão entity -> domain funciona corretamente
        var domain = mapper.toDomain(entity);
        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getNome(), domain.getNome());
        assertEquals(entity.getDescricao(), domain.getDescricao());
        assertEquals(0, entity.getPreco().compareTo(domain.getPreco()));
        assertEquals(entity.getRestauranteId(), domain.getRestauranteId());
        assertEquals(entity.getDisponivelSomenteNoLocal(), domain.isDisponivelSomenteNoLocal());
        assertEquals(entity.getFotoPath(), domain.getFotoPath());
    }
}