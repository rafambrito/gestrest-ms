package br.com.gestrest.pedido.service.adapters.out.integration.dto;

public record PagamentoResponse(
        Long pedidoId,
        Boolean aprovado,
        String mensagem) {
}
