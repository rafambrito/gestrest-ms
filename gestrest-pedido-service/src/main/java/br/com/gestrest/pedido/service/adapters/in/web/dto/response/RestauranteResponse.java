package br.com.gestrest.pedido.service.adapters.in.web.dto.response;

public record RestauranteResponse(Long id, String nome, String endereco, String tipoCozinha, String horarioFuncionamento, Long donoId) {
}
