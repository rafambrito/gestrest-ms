package br.com.gestrest.auth.service.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TipoUsuarioTest {

    @Test
    void criarSucesso() {
        var tipo = TipoUsuario.criar("CLIENTE");
        assertNull(tipo.getId());
        assertEquals("CLIENTE", tipo.getNome());
    }

    @Test
    void existenteIdNuloDeveFalhar() {
        assertThrows(IllegalArgumentException.class, () -> TipoUsuario.existente(null, "GERENTE_RESTAURANTE"));
    }

    @Test
    void atualizarDadosEIgualdade() {
        var tipo = TipoUsuario.existente(2L, "ATENDENTE");
        tipo.atualizarDados("COORDENADOR_ATENDIMENTO");
        assertEquals("COORDENADOR_ATENDIMENTO", tipo.getNome());

        var a = TipoUsuario.existente(1L, "CLIENTE");
        var b = TipoUsuario.existente(1L, "DONO_RESTAURANTE");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void nomeComTamanhoInvalidoDeveFalhar() {
        assertThrows(IllegalArgumentException.class, () -> TipoUsuario.criar("A".repeat(51)));
    }
}
