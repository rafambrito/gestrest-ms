package br.com.gestrest.auth.service.application.usecase.impl.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gestrest.auth.service.application.usecase.command.usuario.CriarUsuarioCommand;
import br.com.gestrest.auth.service.domain.exception.EmailJaCadastradoException;
import br.com.gestrest.auth.service.domain.exception.LoginJaCadastradoException;
import br.com.gestrest.auth.service.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.gestrest.auth.service.domain.model.TipoUsuario;
import br.com.gestrest.auth.service.domain.model.Usuario;
import br.com.gestrest.auth.service.domain.model.ports.out.PasswordEncoderPort;
import br.com.gestrest.auth.service.domain.model.ports.out.TipoUsuarioRepositoryPort;
import br.com.gestrest.auth.service.domain.model.ports.out.UsuarioRepositoryPortRead;
import br.com.gestrest.auth.service.domain.model.ports.out.UsuarioRepositoryPortWrite;

@ExtendWith(MockitoExtension.class)
@DisplayName("CriarUsuarioUseCaseImpl Testes")
class CriarUsuarioUseCaseImplTest {

    @Mock
    private UsuarioRepositoryPortRead usuarioRepositoryRead;

    @Mock
    private UsuarioRepositoryPortWrite usuarioRepositoryWrite;

    @Mock
    private TipoUsuarioRepositoryPort tipoRepository;

    @Mock
    private PasswordEncoderPort passwordEncoder;

    @InjectMocks
    private CriarUsuarioUseCaseImpl useCase;

    @Test
    @DisplayName("Deve criar usuario com sucesso")
    void deveCriarUsuarioComSucesso() {
        var tipoUsuario = TipoUsuario.existente(1L, "GERENTE_RESTAURANTE");
        var command = new CriarUsuarioCommand(
                "João da Silva",
                "joao.silva@gestrest.com",
                "joao.silva",
                "Senha@123",
                1L
        );

        when(tipoRepository.buscarPorId(1L)).thenReturn(Optional.of(tipoUsuario));
        when(passwordEncoder.encode(anyString())).thenAnswer(invocation -> "enc(" + invocation.getArgument(0) + ")");

        var dataCriacao = LocalDateTime.now();
        var usuarioEsperado = Usuario.existente(
                1L,
                "João da Silva",
                "joao.silva@gestrest.com",
                "joao.silva",
                "Senha@123",
                tipoUsuario,
                dataCriacao,
                null
        );
        when(usuarioRepositoryWrite.salvar(any(Usuario.class))).thenReturn(usuarioEsperado);

        var resultado = useCase.criar(command);

        assertEquals("João da Silva", resultado.getNome());
        assertEquals("joao.silva@gestrest.com", resultado.getEmail());
        assertNotNull(resultado.getDataCriacao());
        assertEquals(dataCriacao, resultado.getDataCriacao());
    }

    @Test
    @DisplayName("Deve lancar excecao quando TipoUsuario nao encontrado")
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        var command = new CriarUsuarioCommand(
                "João da Silva",
                "joao.silva@gestrest.com",
                "joao.silva",
                "Senha@123",
                999L
        );

        when(tipoRepository.buscarPorId(999L)).thenReturn(Optional.empty());

        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> useCase.criar(command));
    }

    @Test
    @DisplayName("Deve falhar quando email já existe")
    void deveFalharQuandoEmailDuplicado() {
        var tipoUsuario = TipoUsuario.existente(1L, "GERENTE_RESTAURANTE");
        var command = new CriarUsuarioCommand(
                "João da Silva",
                "joao.silva@gestrest.com",
                "joao.silva",
                "Senha@123",
                1L
        );

        when(tipoRepository.buscarPorId(1L)).thenReturn(Optional.of(tipoUsuario));
        when(usuarioRepositoryRead.buscarPorEmail("joao.silva@gestrest.com")).thenReturn(Optional.of(
                Usuario.existente(2L, "João da Silva", "joao.silva@gestrest.com", "joao.silva", "Senha@987", tipoUsuario)
        ));

        assertThrows(EmailJaCadastradoException.class, () -> useCase.criar(command));
    }

    @Test
    @DisplayName("Deve falhar quando login já existe")
    void deveFalharQuandoLoginDuplicado() {
        var tipoUsuario = TipoUsuario.existente(1L, "GERENTE_RESTAURANTE");
        var command = new CriarUsuarioCommand(
                "João da Silva",
                "joao.silva@gestrest.com",
                "joao.silva",
                "Senha@123",
                1L
        );

        when(tipoRepository.buscarPorId(1L)).thenReturn(Optional.of(tipoUsuario));
        when(usuarioRepositoryRead.buscarPorEmail("joao.silva@gestrest.com")).thenReturn(Optional.empty());
        when(usuarioRepositoryRead.buscarPorLogin("joao.silva")).thenReturn(Optional.of(
                Usuario.existente(3L, "João da Silva", "joao.silva@gestrest.com", "joao.silva", "Senha@456", tipoUsuario)
        ));

        assertThrows(LoginJaCadastradoException.class, () -> useCase.criar(command));
    }
}
