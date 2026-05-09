package br.com.gestrest.pedido.service.adapters.out.event;

import org.springframework.stereotype.Component;

import br.com.gestrest.pedido.service.domain.model.ports.out.PagamentoEventPublisherPort;

@Component
public class MockPagamentoEventPublisherAdapter implements PagamentoEventPublisherPort {

    @Override
    public void publicarPagamentoAprovado(Long pedidoId, Long usuarioId) {
        // Estrutura preparada para envio de evento pagamento.aprovado.
    }

    @Override
    public void publicarPagamentoPendente(Long pedidoId, Long usuarioId) {
        // Estrutura preparada para envio de evento pagamento.pendente.
    }
}
