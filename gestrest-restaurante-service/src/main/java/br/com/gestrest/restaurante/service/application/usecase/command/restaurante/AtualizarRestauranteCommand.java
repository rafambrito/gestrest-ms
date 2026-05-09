package br.com.gestrest.restaurante.service.application.usecase.command.restaurante;

public record AtualizarRestauranteCommand(
  String nome, String endereco, String tipoCozinha, String horarioFuncionamento, boolean ativo) {
}
