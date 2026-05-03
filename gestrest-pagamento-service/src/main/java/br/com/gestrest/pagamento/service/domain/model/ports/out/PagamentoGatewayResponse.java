package br.com.gestrest.pagamento.service.domain.model.ports.out;

public record PagamentoGatewayResponse(String pagamentoIdExterno, String status) {
}
