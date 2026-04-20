package br.com.gestrest.restaurante.service.domain.model;

import java.util.Objects;

public class Restaurante {

    private static final int NOME_MAX_LENGTH = 150;

    private Long id;
    private String nome;
    private boolean ativo;

    private Restaurante(Long id, String nome, boolean ativo) {
        validarNome(nome);
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }

    public static Restaurante criar(String nome) {
        return new Restaurante(null, nome, true);
    }

    public static Restaurante existente(Long id, String nome, boolean ativo) {
        if (id == null) {
            throw new IllegalArgumentException("Id do restaurante é obrigatório");
        }
        return new Restaurante(id, nome, ativo);
    }

    public void atualizar(String nome, boolean ativo) {
        validarNome(nome);
        this.nome = nome;
        this.ativo = ativo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    private static void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (nome.length() > NOME_MAX_LENGTH) {
            throw new IllegalArgumentException("Nome deve ter no máximo 150 caracteres");
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Restaurante other)) {
            return false;
        }
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
