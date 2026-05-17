package br.com.gestrest.auth.service.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Usuario testes de dominio")
class UsuarioTest {

    @Test
    @DisplayName("isAdmin e isCliente devem ser baseados no ID do TipoUsuarioEnum")
    void criarEFlags() {
        var tipoAdmin = TipoUsuario.existente(TipoUsuarioEnum.ADMIN.getId(), "ADMIN");
        var tipoCliente = TipoUsuario.existente(TipoUsuarioEnum.CLIENTE.getId(), "CLIENTE");

        var admin = Usuario.criar("Rafael Brito", "rafael.brito@gestrest.com", "rafael.brito", "Senha@123", tipoAdmin);
        var cliente = Usuario.criar("José Pereira", "jose.pereira@gestrest.com", "jose.pereira", "Senha@456", tipoCliente);

        assertTrue(admin.isDono());
        assertFalse(admin.isCliente());
        assertTrue(cliente.isCliente());
        assertFalse(cliente.isDono());
    }

    @Test
    @DisplayName("alterarTipoUsuario deve refletir no isAdmin e atualizarDados altera o nome")
    void alterarTipoEAtualizarDados() {
        var tipo = TipoUsuario.existente(TipoUsuarioEnum.CLIENTE.getId(), "CLIENTE");
        var usuario = Usuario.criar("Rafael Brito", "rafael.brito@gestrest.com", "rafael.brito", "Senha@789", tipo);
        assertTrue(usuario.isCliente());

        var novoAdmin = TipoUsuario.existente(TipoUsuarioEnum.ADMIN.getId(), "ADMIN");
        usuario.alterarTipoUsuario(novoAdmin);
        assertEquals("ADMIN", usuario.getTipoUsuario().getNome());
        assertTrue(usuario.isDono());
        assertFalse(usuario.isCliente());

        usuario.atualizarDados("Rafael Brito");
        assertEquals("Rafael Brito", usuario.getNome());
    }

    @Test
    @DisplayName("criar deve inicializar dataCriacao e deixar dataUltimaAlteracao nula")
    void criarDeveSetarDataCriacao() {
        var tipo = TipoUsuario.existente(1L, "CLIENTE");
        var before = LocalDateTime.now().minusSeconds(1);

        var usuario = Usuario.criar("João da Silva", "joao.silva@gestrest.com", "joao.silva", "Senha@111", tipo);

        assertNotNull(usuario.getDataCriacao());
        assertNull(usuario.getDataUltimaAlteracao());
        assertTrue(usuario.getDataCriacao().isAfter(before));
    }

    @Test
    @DisplayName("atualizarDados deve setar dataUltimaAlteracao")
    void atualizarDadosDeveSetarDataUltimaAlteracao() {
        var tipo = TipoUsuario.existente(1L, "CLIENTE");
        var usuario = Usuario.criar("João da Silva", "joao.silva@gestrest.com", "joao.silva", "Senha@111", tipo);
        var before = LocalDateTime.now().minusSeconds(1);

        usuario.atualizarDados("Ana Atualizada");

        assertNotNull(usuario.getDataUltimaAlteracao());
        assertTrue(usuario.getDataUltimaAlteracao().isAfter(before));
        assertEquals("Ana Atualizada", usuario.getNome());
    }

    @Test
    @DisplayName("atualizar deve setar dataUltimaAlteracao e atualizar os campos")
    void atualizarDeveSetarDataUltimaAlteracao() {
        var tipo = TipoUsuario.existente(1L, "CLIENTE");
        var novoTipo = TipoUsuario.existente(2L, "ADMIN");
        var usuario = Usuario.criar("João da Silva", "joao.silva@gestrest.com", "joao.silva", "Senha@111", tipo);
        var before = LocalDateTime.now().minusSeconds(1);

        usuario.atualizar("João da Silva", "joao.silva@gestrest.com", novoTipo);

        assertNotNull(usuario.getDataUltimaAlteracao());
        assertTrue(usuario.getDataUltimaAlteracao().isAfter(before));
        assertEquals("ADMIN", usuario.getTipoUsuario().getNome());
    }

    @Test
    @DisplayName("regras obrigatórias devem ser validadas")
    void criarDeveValidarCamposObrigatorios() {
        var tipo = TipoUsuario.existente(1L, "CLIENTE");

        assertThrows(IllegalArgumentException.class,
                () -> Usuario.criar(null, "joao.silva@gestrest.com", "joao.silva", "Senha@123", tipo));
        assertThrows(IllegalArgumentException.class,
                () -> Usuario.criar("João da Silva", "email-invalido", "joao.silva", "Senha@123", tipo));
        assertThrows(IllegalArgumentException.class,
                () -> Usuario.criar("João da Silva", "joao.silva@gestrest.com", " ", "Senha@123", tipo));
        assertThrows(IllegalArgumentException.class,
                () -> Usuario.criar("João da Silva", "joao.silva@gestrest.com", "joao.silva", "123", tipo));
        assertThrows(IllegalArgumentException.class,
                () -> Usuario.criar("João da Silva", "joao.silva@gestrest.com", "joao.silva", "Senha@123", null));
    }
}
