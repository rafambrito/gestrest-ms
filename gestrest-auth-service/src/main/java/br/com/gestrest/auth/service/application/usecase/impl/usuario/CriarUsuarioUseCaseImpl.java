package br.com.gestrest.auth.service.application.usecase.impl.usuario;

import br.com.gestrest.auth.service.application.usecase.command.usuario.CriarUsuarioCommand;
import br.com.gestrest.auth.service.domain.exception.EmailJaCadastradoException;
import br.com.gestrest.auth.service.domain.exception.LoginJaCadastradoException;
import br.com.gestrest.auth.service.domain.exception.TipoUsuarioNaoEncontradoException;
import br.com.gestrest.auth.service.domain.model.Usuario;
import br.com.gestrest.auth.service.domain.model.ports.in.usuario.CriarUsuarioUseCase;
import br.com.gestrest.auth.service.domain.model.ports.out.PasswordEncoderPort;
import br.com.gestrest.auth.service.domain.model.ports.out.TipoUsuarioRepositoryPort;
import br.com.gestrest.auth.service.domain.model.ports.out.UsuarioRepositoryPortRead;
import br.com.gestrest.auth.service.domain.model.ports.out.UsuarioRepositoryPortWrite;

public class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final UsuarioRepositoryPortRead usuarioRepositoryRead;
    private final UsuarioRepositoryPortWrite usuarioRepositoryWrite;
    private final TipoUsuarioRepositoryPort tipoRepository;
    private final PasswordEncoderPort passwordEncoder;

    public CriarUsuarioUseCaseImpl(
            UsuarioRepositoryPortRead usuarioRepositoryRead,
            UsuarioRepositoryPortWrite usuarioRepositoryWrite,
            TipoUsuarioRepositoryPort tipoRepository,
            PasswordEncoderPort passwordEncoder) {
        this.usuarioRepositoryRead = usuarioRepositoryRead;
        this.usuarioRepositoryWrite = usuarioRepositoryWrite;
        this.tipoRepository = tipoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario criar(CriarUsuarioCommand command) {
        var tipo = tipoRepository.buscarPorId(command.tipoUsuarioId())
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(command.tipoUsuarioId()));

        var email = command.email();
        var login = command.login();

        if (email != null && usuarioRepositoryRead.buscarPorEmail(email).isPresent()) {
            throw new EmailJaCadastradoException();
        }

        if (login != null && usuarioRepositoryRead.buscarPorLogin(login).isPresent()) {
            throw new LoginJaCadastradoException();
        }

        var senhaCodificada = passwordEncoder.encode(command.senha());

        var usuario = Usuario.criar(
                command.nome(),
                email,
                login,
                senhaCodificada,
                tipo
        );

        return usuarioRepositoryWrite.salvar(usuario);
    }
}
