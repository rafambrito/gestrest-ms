package br.com.gestrest.restaurante.service.adapters.in.web.dto.response;

public record RestauranteResponse(
    Long id,
    String nome,
    String endereco,
    String tipoCozinha,
    String horarioFuncionamento,
    Boolean ativo,
    Long donoId) {
}
