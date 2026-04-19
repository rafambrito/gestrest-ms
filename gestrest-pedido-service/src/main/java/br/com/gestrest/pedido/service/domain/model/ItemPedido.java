package br.com.gestrest.pedido.service.domain.model;

import java.math.BigDecimal;

public class ItemPedido {

    private Long itemCardapioId;
    private String nomeItem;
    private int quantidade;
    private BigDecimal precoUnitario;

    private ItemPedido(Long itemCardapioId, String nomeItem, int quantidade, BigDecimal precoUnitario) {
        if (itemCardapioId == null) {
            throw new IllegalArgumentException("Item do cardapio e obrigatorio");
        }
        if (nomeItem == null || nomeItem.isBlank()) {
            throw new IllegalArgumentException("Nome do item e obrigatorio");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preco unitario deve ser maior que zero");
        }
        this.itemCardapioId = itemCardapioId;
        this.nomeItem = nomeItem;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public static ItemPedido criar(Long itemCardapioId, String nomeItem, int quantidade, BigDecimal precoUnitario) {
        return new ItemPedido(itemCardapioId, nomeItem, quantidade, precoUnitario);
    }

    public BigDecimal subtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public Long getItemCardapioId() {
        return itemCardapioId;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }
}
