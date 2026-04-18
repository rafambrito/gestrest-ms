package br.com.gestrest.auth.service.application.usecase.impl.usuario;

import br.com.gestrest.auth.service.application.usecase.command.usuario.AutenticarUsuarioCommand;
import br.com.gestrest.auth.service.domain.exception.AutenticacaoException;
import br.com.gestrest.auth.service.domain.model.Usuario;
import br.com.gestrest.auth.service.domain.model.ports.in.usuario.AutenticarUsuarioUseCase;
import br.com.gestrest.auth.service.domain.model.ports.out.JwtTokenProviderPort;
import br.com.gestrest.auth.service.domain.model.ports.out.PasswordEncoderPort;
import br.com.gestrest.auth.service.domain.model.ports.out.UsuarioRepositoryPortRead;

public class AutenticarUsuarioUseCaseImpl implements AutenticarUsuarioUseCase {

    private final UsuarioRepositoryPortRead usuarioRepositoryRead;
    private final PasswordEncoderPort passwordEncoder;
    private final JwtTokenProviderPort jwtTokenProvider;

    public AutenticarUsuarioUseCaseImpl(
            UsuarioRepositoryPortRead usuarioRepositoryRead,
            PasswordEncoderPort passwordEncoder,
            JwtTokenProviderPort jwtTokenProvider) {
        this.usuarioRepositoryRead = usuarioRepositoryRead;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String autenticar(AutenticarUsuarioCommand command) {
        var loginOuEmail = command.loginOuEmail();
        var senha = command.senha();

        Usuario usuario = buscarUsuario(loginOuEmail);

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new AutenticacaoException("Credenciais inválidas");
        }

        return jwtTokenProvider.generateToken(usuario);
    }

    private Usuario buscarUsuario(String loginOuEmail) {
        return usuarioRepositoryRead.buscarPorLogin(loginOuEmail)
                .or(() -> usuarioRepositoryRead.buscarPorEmail(loginOuEmail))
                .orElseThrow(() -> new AutenticacaoException("Usuário não encontrado"));
    }
}