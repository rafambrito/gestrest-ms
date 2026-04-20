package br.com.gestrest.restaurante.service.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.gestrest.restaurante.service.domain.model.Restaurante;

@DisplayName("Restaurante testes de dominio")
class RestauranteTest {

    @Test
    void criarSucesso() {
        var r = Restaurante.criar("João da Silva");
        assertNull(r.getId());
        assertEquals("João da Silva", r.getNome());
        assertTrue(r.isAtivo());
    }

    @Test
    void validarCamposObrigatorios() {
        assertThrows(IllegalArgumentException.class, () -> Restaurante.criar(null));
        assertThrows(IllegalArgumentException.class, () -> Restaurante.criar(""));
        assertThrows(IllegalArgumentException.class, () -> Restaurante.criar("   "));
    }

    @Test
    void atualizarSucesso() {
        var r = Restaurante.existente(3L, "José Pereira", true);
        r.atualizar("Rafael Brito", false);
        assertEquals("Rafael Brito", r.getNome());
        assertFalse(r.isAtivo());
    }

    @Test
    void equalsHashCode() {
        var a = Restaurante.existente(1L, "João da Silva", true);
        var b = Restaurante.existente(1L, "Rafael Brito", false);
        var c = Restaurante.existente(2L, "João da Silva", true);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }
}
