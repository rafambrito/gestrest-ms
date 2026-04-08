package br.com.gestrest.auth.service.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import br.com.gestrest.auth.service.adapters.out.persistence.entity.UsuarioEntity;
import br.com.gestrest.auth.service.domain.model.TipoUsuario;
import br.com.gestrest.auth.service.domain.model.Usuario;

@Component
public class UsuarioPersistenceMapper {

    public Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) {
            return null;
        }

        var tipoEntity = entity.getTipoUsuario();
        TipoUsuario tipoUsuario = tipoEntity != null
                ? TipoUsuario.existente(tipoEntity.getId(), tipoEntity.getNome())
                : null;

        return Usuario.existente(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getLogin(),
                entity.getSenha(),
                tipoUsuario,
                entity.getDataCriacao(),
                entity.getDataUltimaAlteracao()
        );
    }
}
