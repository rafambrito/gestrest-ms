package br.com.gestrest.api.adapters.in.web.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.com.gestrest.pedido.service.adapters.in.web.dto.request.AtualizarRestauranteRequest;
import br.com.gestrest.pedido.service.adapters.in.web.dto.request.CriarRestauranteRequest;
import br.com.gestrest.pedido.service.adapters.in.web.dto.response.RestauranteResponse;
import br.com.gestrest.pedido.service.adapters.in.web.mapper.RestauranteWebMapper;
import br.com.gestrest.pedido.service.domain.model.Restaurante;

class RestauranteWebMapperTest {

    private final RestauranteWebMapper mapper = new RestauranteWebMapper();

    @Test
    void deveMapearCreateRequestParaDomainCorretamente() {
        var req = new CriarRestauranteRequest("João da Silva", "Rua das Rosas, São Paulo/SP", "Italiana", "9-18", 10L);
        var domain = mapper.toDomain(req);

        // Testa comportamento: criação não tem ID definido
        assertNotNull(domain);
        assertNull(domain.getId());
        assertEquals("João da Silva", domain.getNome());
        assertEquals("Rua das Rosas, São Paulo/SP", domain.getEndereco());
        assertEquals("Italiana", domain.getTipoCozinha());
        assertEquals("9-18", domain.getHorarioFuncionamento());
        assertEquals(10L, domain.getDonoId().longValue());
    }

    @Test
    void deveMapearUpdateRequestParaDomainComId() {
        var req = new AtualizarRestauranteRequest("Rafael Brito", "Rua das Rosas, São Paulo/SP", "Brasileira", "10-20", 7L);
        var domain = mapper.toDomain(7L, req);

        // Testa comportamento: update preserva ID fornecido
        assertNotNull(domain);
        assertEquals(7L, domain.getId());
        assertEquals("Rafael Brito", domain.getNome());
        assertEquals("Rua das Rosas, São Paulo/SP", domain.getEndereco());
        assertEquals("Brasileira", domain.getTipoCozinha());
        assertEquals("10-20", domain.getHorarioFuncionamento());
        assertEquals(7L, domain.getDonoId().longValue());
    }

    @Test
    void deveMapearDomainParaResponseCorretamente() {
        var domain = Restaurante.existente(5L, "João da Silva", "Rua das Rosas, São Paulo/SP", "Churrascaria", "11:00-23:00", 2L);
        RestauranteResponse resp = mapper.toResponse(domain);

        // Testa comportamento: response contém todas as informações do domain
        assertNotNull(resp);
        assertEquals(5L, resp.id().longValue());
        assertEquals("João da Silva", resp.nome());
        assertEquals("Rua das Rosas, São Paulo/SP", resp.endereco());
        assertEquals("Churrascaria", resp.tipoCozinha());
        assertEquals("11:00-23:00", resp.horarioFuncionamento());
        assertEquals(2L, resp.donoId().longValue());
    }
}