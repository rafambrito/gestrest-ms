package br.com.gestrest.pedido.service.domain.model.ports.out;

public interface PagamentoEventPublisherPort {
    void publicarPagamentoAprovado(Long pedidoId, Long usuarioId);

    void publicarPagamentoPendente(Long pedidoId, Long usuarioId);
}
