package br.com.gestrest.pagamento.service.domain.model.event;

public record PagamentoAprovadoEvent(Long pedidoId, Long usuarioId) {
}
