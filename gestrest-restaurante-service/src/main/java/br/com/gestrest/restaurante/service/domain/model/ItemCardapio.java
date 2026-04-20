package br.com.gestrest.restaurante.service.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import br.com.gestrest.restaurante.service.domain.exception.ItemCardapioInvalidoException;

public class ItemCardapio {

    private static final int NOME_MAX_LENGTH = 150;
    private static final int DESCRICAO_MAX_LENGTH = 500;

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean ativo;
    private Long restauranteId;

    private ItemCardapio(Long id, String nome, String descricao, BigDecimal preco, boolean ativo, Long restauranteId) {
        validar(nome, descricao, preco, restauranteId);
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
        this.restauranteId = restauranteId;
    }

    public static ItemCardapio criar(String nome, String descricao, BigDecimal preco, Long restauranteId) {
        return new ItemCardapio(null, nome, descricao, preco, true, restauranteId);
    }

    public static ItemCardapio existente(Long id, String nome, String descricao, BigDecimal preco, boolean ativo,
            Long restauranteId) {
        if (id == null) {
            throw new ItemCardapioInvalidoException("Id do item de cardápio é obrigatório");
        }
        return new ItemCardapio(id, nome, descricao, preco, ativo, restauranteId);
    }

    public void atualizar(String nome, String descricao, BigDecimal preco, boolean ativo, Long restauranteId) {
        validar(nome, descricao, preco, restauranteId);
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
        this.restauranteId = restauranteId;
    }

    private static void validar(String nome, String descricao, BigDecimal preco, Long restauranteId) {
        if (nome == null || nome.isBlank()) {
            throw new ItemCardapioInvalidoException("Nome é obrigatório");
        }
        if (nome.length() > NOME_MAX_LENGTH) {
            throw new ItemCardapioInvalidoException("Nome deve ter no máximo 150 caracteres");
        }
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ItemCardapioInvalidoException("Preço deve ser maior que zero");
        }
        if (restauranteId == null) {
            throw new ItemCardapioInvalidoException("Restaurante é obrigatório");
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemCardapio other)) {
            return false;
        }
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
