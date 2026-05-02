package br.com.gestrest.api.adapters.in.web.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.gestrest.pedido.service.adapters.in.web.dto.request.AtualizarItemCardapioRequest;
import br.com.gestrest.pedido.service.adapters.in.web.dto.request.CriarItemCardapioRequest;
import br.com.gestrest.pedido.service.adapters.in.web.dto.response.ItemCardapioResponse;
import br.com.gestrest.pedido.service.adapters.in.web.mapper.ItemCardapioWebMapper;
import br.com.gestrest.pedido.service.domain.model.ItemCardapio;

class ItemCardapioWebMapperTest {

    private final ItemCardapioWebMapper mapper = new ItemCardapioWebMapper();

    @Test
    void deveMapearRequestParaDomainCorretamente() {
        var req = new CriarItemCardapioRequest("Camarão na Moranga", "Camarao refogado servido na moranga", BigDecimal.TEN,
                true, "/imagens/itens/camarao-moranga.jpg", 4L);
        var domain = mapper.toDomain(req);

        // Testa comportamento: o mapeamento preserva todas as informações necessárias
        assertNotNull(domain);
        assertEquals("Camarão na Moranga", domain.getNome());
        assertEquals("Camarao refogado servido na moranga", domain.getDescricao());
        assertEquals(0, BigDecimal.TEN.compareTo(domain.getPreco()));
        assertEquals(true, domain.isDisponivelSomenteNoLocal());
        assertEquals("/imagens/itens/camarao-moranga.jpg", domain.getFotoPath());
        assertEquals(4L, domain.getRestauranteId().longValue());
    }

    @Test
    void deveMapearUpdateRequestParaDomainComId() {
        var req = new AtualizarItemCardapioRequest("NU", "DU", BigDecimal.valueOf(5), false, "/itens/nu.jpg", 8L);
        var domain = mapper.toDomain(3L, req);

        // Testa comportamento: update preserva ID e mapeia novos valores
        assertNotNull(domain);
        assertEquals(3L, domain.getId());
        assertEquals("NU", domain.getNome());
        assertEquals("DU", domain.getDescricao());
        assertEquals(0, BigDecimal.valueOf(5).compareTo(domain.getPreco()));
        assertEquals(false, domain.isDisponivelSomenteNoLocal());
        assertEquals("/itens/nu.jpg", domain.getFotoPath());
        assertEquals(8L, domain.getRestauranteId().longValue());
    }

    @Test
    void deveMapearDomainParaResponseCorretamente() {
        var domain = ItemCardapio.existente(7L, "Bruschetta de Tomate", "Pao italiano, tomate confit e manjericao", BigDecimal.valueOf(23.5),
                6L, false, "/itens/bruschetta.jpg");
        ItemCardapioResponse resp = mapper.toResponse(domain);

        // Testa comportamento: response contém todas as informações do domain
        assertNotNull(resp);
        assertEquals(7L, resp.id().longValue());
        assertEquals("Bruschetta de Tomate", resp.nome());
        assertEquals("Pao italiano, tomate confit e manjericao", resp.descricao());
        assertEquals(0, BigDecimal.valueOf(23.5).compareTo(resp.preco()));
        assertEquals(false, resp.disponivelSomenteNoLocal());
        assertEquals("/itens/bruschetta.jpg", resp.fotoPath());
        assertEquals(6L, resp.restauranteId().longValue());
    }
}
