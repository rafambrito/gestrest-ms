package br.com.gestrest.auth.service.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import br.com.gestrest.auth.service.adapters.out.persistence.entity.TipoUsuarioEntity;
import br.com.gestrest.auth.service.domain.model.TipoUsuario;

@Component
public class TipoUsuarioPersistenceMapper {

    public TipoUsuario toDomain(TipoUsuarioEntity entity) {
        if (entity == null) {
            return null;
        }

        return TipoUsuario.existente(entity.getId(), entity.getNome());
    }
}
