package br.com.gestrest.restaurante.service.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.gestrest.restaurante.service.domain.exception.ItemCardapioInvalidoException;

@DisplayName("ItemCardapio testes de dominio")
class ItemCardapioTest {

    @Test
    @DisplayName("Criar item com sucesso")
    void criarSucesso() {
        var item = ItemCardapio.criar("João da Silva", "Acompanha pure de batata", new BigDecimal("49.90"), 1L);
        assertNull(item.getId());
        assertEquals("João da Silva", item.getNome());
        assertEquals("Acompanha pure de batata", item.getDescricao());
        assertEquals(0, item.getPreco().compareTo(new BigDecimal("49.90")));
        assertEquals(1L, item.getRestauranteId());
        assertTrue(item.isAtivo());
    }

    @Test
    @DisplayName("Falha quando nome nulo ou em branco")
    void nomeInvalido() {
        assertThrows(ItemCardapioInvalidoException.class,
            () -> ItemCardapio.criar(null, "Prato com ingredientes frescos", new BigDecimal("29.90"), 1L));
        assertThrows(ItemCardapioInvalidoException.class,
            () -> ItemCardapio.criar("   ", "Prato com ingredientes frescos", new BigDecimal("29.90"), 1L));
    }

    @Test
    @DisplayName("Falha quando preco nulo ou menor/igual zero")
    void precoInvalido() {
        assertThrows(ItemCardapioInvalidoException.class,
            () -> ItemCardapio.criar("Penne ao Pomodoro", "Massa artesanal com molho de tomate fresco", null, 1L));
        assertThrows(ItemCardapioInvalidoException.class,
            () -> ItemCardapio.criar("Penne ao Pomodoro", "Massa artesanal com molho de tomate fresco", new BigDecimal("0.00"), 1L));
        assertThrows(ItemCardapioInvalidoException.class,
            () -> ItemCardapio.criar("Penne ao Pomodoro", "Massa artesanal com molho de tomate fresco", new BigDecimal("-1"), 1L));
    }

    @Test
    @DisplayName("Falha quando restauranteId nulo")
    void restauranteIdNulo() {
        assertThrows(ItemCardapioInvalidoException.class,
            () -> ItemCardapio.criar("Penne ao Pomodoro", "Massa artesanal com molho de tomate fresco", new BigDecimal("29.90"), null));
    }

    @Test
    @DisplayName("Atualizar item atualiza campos")
    void atualizarSucesso() {
        var item = ItemCardapio.existente(5L, "José Pereira", "Pao brioche, queijo prato", new BigDecimal("28.00"), true, 2L);
        item.atualizar("Rafael Brito", "Pao brioche, cheddar, bacon", new BigDecimal("34.50"), false, 2L);
        assertEquals("Rafael Brito", item.getNome());
        assertEquals("Pao brioche, cheddar, bacon", item.getDescricao());
        assertEquals(0, item.getPreco().compareTo(new BigDecimal("34.50")));
        assertFalse(item.isAtivo());
    }

    @Test
    @DisplayName("Equals e hashCode baseados em id")
    void equalsHashCode() {
        var a = ItemCardapio.existente(1L, "José Pereira", "Nhoque artesanal", new BigDecimal("36.00"), true, 1L);
        var b = ItemCardapio.existente(1L, "Salmão ao Molho", "File de salmao", new BigDecimal("69.00"), false, 2L);
        var c = ItemCardapio.existente(2L, "José Pereira", "Nhoque artesanal", new BigDecimal("36.00"), true, 1L);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }
}
