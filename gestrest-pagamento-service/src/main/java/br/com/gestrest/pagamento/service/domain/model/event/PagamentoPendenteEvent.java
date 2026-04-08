package br.com.gestrest.pagamento.service.domain.model.event;

public record PagamentoPendenteEvent(Long pedidoId, Long usuarioId) {
}
