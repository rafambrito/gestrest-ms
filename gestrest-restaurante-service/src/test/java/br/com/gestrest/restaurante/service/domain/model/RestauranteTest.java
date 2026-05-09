package br.com.gestrest.restaurante.service.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.gestrest.restaurante.service.domain.model.Restaurante;

@DisplayName("Restaurante testes de dominio")
class RestauranteTest {

    @Test
    void criarSucesso() {
        var r = Restaurante.criar("João da Silva"   , "Rua das Flores, 123", "Brasileira", "10:00 - 22:00", 1L, true);
        assertNull(r.getId());
        assertEquals("João da Silva", r.getNome());
        assertTrue(r.isAtivo());
    }

    @Test
    void validarCamposObrigatorios() {
        assertThrows(IllegalArgumentException.class, () -> Restaurante.criar(null, "Rua das Flores, 123", "Brasileira", "10:00 - 22:00", 1L, true));
        assertThrows(IllegalArgumentException.class, () -> Restaurante.criar("", "Rua das Flores, 123", "Brasileira", "10:00 - 22:00", 1L, true));
        assertThrows(IllegalArgumentException.class, () -> Restaurante.criar("   ", "Rua das Flores, 123", "Brasileira", "10:00 - 22:00", 1L, true));
    }

    @Test
    void atualizarSucesso() {
        var r = Restaurante.existente(3L, "José Pereira", "Rua das Flores, 123", "Brasileira", "10:00 - 22:00", 1L, true);
        r.atualizar("Rafael Brito", "Rua das Flores, 123", "Brasileira", "10:00 - 22:00", false);
        assertEquals("Rafael Brito", r.getNome());
        assertFalse(r.isAtivo());
    }

    @Test
    void equalsHashCode() {
        var a = Restaurante.existente(1L, "João da Silva", "Rua das Flores, 123", "Brasileira", "10:00 - 22:00", 1L, true);
        var b = Restaurante.existente(1L, "Rafael Brito", "Rua das Flores, 123", "Brasileira", "10:00 - 22:00", 1L, false);
        var c = Restaurante.existente(2L, "João da Silva", "Rua das Flores, 123", "Brasileira", "10:00 - 22:00", 1L, true);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }
}
