package br.com.gestrest.restaurante.service.application.usecase.command.restaurante;

public record CriarRestauranteCommand(String nome,  String endereco, String tipoCozinha, String horarioFuncionamento, Long donoId) {
}
