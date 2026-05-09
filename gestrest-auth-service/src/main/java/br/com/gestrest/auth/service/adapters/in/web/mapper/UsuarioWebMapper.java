package br.com.gestrest.auth.service.adapters.in.web.mapper;

import org.springframework.stereotype.Component;

import br.com.gestrest.auth.service.adapters.in.web.dto.request.CriarUsuarioRequest;
import br.com.gestrest.auth.service.adapters.in.web.dto.response.TipoUsuarioResponse;
import br.com.gestrest.auth.service.adapters.in.web.dto.response.UsuarioResponse;
import br.com.gestrest.auth.service.application.usecase.command.usuario.CriarUsuarioCommand;
import br.com.gestrest.auth.service.domain.model.Usuario;

@Component
public class UsuarioWebMapper {

    public CriarUsuarioCommand toDomain(CriarUsuarioRequest request) {
        if (request == null) {
            return null;
        }

        return new CriarUsuarioCommand(
                request.nome() != null ? request.nome().trim() : null,
                request.email() != null ? request.email().trim().toLowerCase() : null,
                request.login() != null ? request.login().trim().toLowerCase() : null,
                request.senha(),
                request.tipoUsuarioId()
        );
    }

    public UsuarioResponse toResponse(Usuario domain) {
        if (domain == null) {
            return null;
        }

        TipoUsuarioResponse tipoUsuarioResponse = null;
        if (domain.getTipoUsuario() != null) {
            tipoUsuarioResponse = new TipoUsuarioResponse(
                    domain.getTipoUsuario().getId(),
                    domain.getTipoUsuario().getNome()
            );
        }

        return new UsuarioResponse(
                domain.getId(),
                domain.getNome(),
                domain.getEmail(),
                domain.getLogin(),
                tipoUsuarioResponse,
                domain.getDataCriacao(),
                domain.getDataUltimaAlteracao()
        );
    }
}
