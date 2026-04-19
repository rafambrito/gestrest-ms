package br.com.gestrest.pedido.service.adapters.in.web.dto.request;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CriarPedidoRequest(
        @NotNull(message = "Usuario e obrigatorio") Long usuarioId,
        @NotNull(message = "Restaurante e obrigatorio") Long restauranteId,
        @NotEmpty(message = "Pedido deve conter itens") List<@Valid ItemPedidoRequest> itens) {

    public record ItemPedidoRequest(
            @NotNull(message = "Item cardapio e obrigatorio") Long itemCardapioId,
            @NotBlank(message = "Nome do item e obrigatorio") String nomeItem,
            @Positive(message = "Quantidade deve ser maior que zero") int quantidade,
            @NotNull(message = "Preco unitario e obrigatorio") @Positive(message = "Preco unitario deve ser maior que zero") BigDecimal precoUnitario) {
    }
}
