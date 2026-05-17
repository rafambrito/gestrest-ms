package br.com.gestrest.auth.service.domain.model;

import java.util.Arrays;
import java.util.Optional;

public enum TipoUsuarioEnum {

    ADMIN(1L, "Administrador"),
    CLIENTE(2L, "Cliente");

    private final Long id;
    private final String descricao;

    TipoUsuarioEnum(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Optional<TipoUsuarioEnum> fromId(Long id) {
        if (id == null) {
            return Optional.empty();
        }

        return Arrays.stream(values())
                .filter(value -> value.id.equals(id))
                .findFirst();
    }
}