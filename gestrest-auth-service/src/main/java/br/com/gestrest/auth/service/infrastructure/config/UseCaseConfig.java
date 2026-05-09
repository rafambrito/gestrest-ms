package br.com.gestrest.auth.service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gestrest.auth.service.application.usecase.impl.usuario.CriarUsuarioUseCaseImpl;
import br.com.gestrest.auth.service.application.usecase.impl.usuario.AutenticarUsuarioUseCaseImpl;
import br.com.gestrest.auth.service.domain.model.ports.in.usuario.CriarUsuarioUseCase;
import br.com.gestrest.auth.service.domain.model.ports.in.usuario.AutenticarUsuarioUseCase;
import br.com.gestrest.auth.service.domain.model.ports.out.JwtTokenProviderPort;
import br.com.gestrest.auth.service.domain.model.ports.out.PasswordEncoderPort;
import br.com.gestrest.auth.service.domain.model.ports.out.TipoUsuarioRepositoryPort;
import br.com.gestrest.auth.service.domain.model.ports.out.UsuarioRepositoryPortRead;
import br.com.gestrest.auth.service.domain.model.ports.out.UsuarioRepositoryPortWrite;

@Configuration
public class UseCaseConfig {

    @Bean
    public CriarUsuarioUseCase criarUsuarioUseCase(
            UsuarioRepositoryPortRead usuarioRepositoryRead,
            UsuarioRepositoryPortWrite usuarioRepositoryWrite,
            TipoUsuarioRepositoryPort tipoRepository,
            PasswordEncoderPort passwordEncoder) {
        return new CriarUsuarioUseCaseImpl(usuarioRepositoryRead, usuarioRepositoryWrite, tipoRepository,
                passwordEncoder);
    }

    @Bean
    public AutenticarUsuarioUseCase autenticarUsuarioUseCase(
            UsuarioRepositoryPortRead usuarioRepositoryRead,
            PasswordEncoderPort passwordEncoder,
            JwtTokenProviderPort jwtTokenProvider) {
        return new AutenticarUsuarioUseCaseImpl(usuarioRepositoryRead, passwordEncoder, jwtTokenProvider);
    }
}
