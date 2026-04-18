package br.com.gestrest.pagamento.service.adapters.out.event;

import org.springframework.stereotype.Component;

import br.com.gestrest.pagamento.service.domain.model.event.PagamentoAprovadoEvent;
import br.com.gestrest.pagamento.service.domain.model.event.PagamentoPendenteEvent;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoEventPublisherPort;

@Component
public class MockPagamentoEventPublisherAdapter implements PagamentoEventPublisherPort {

    @Override
    public void publicarAprovado(PagamentoAprovadoEvent event) {
        // Estrutura preparada para evento pagamento.aprovado.
    }

    @Override
    public void publicarPendente(PagamentoPendenteEvent event) {
        // Estrutura preparada para evento pagamento.pendente.
    }
}
