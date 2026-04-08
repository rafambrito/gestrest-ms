package br.com.gestrest.pagamento.service.domain.ports.out;

import br.com.gestrest.pagamento.service.domain.model.event.PagamentoAprovadoEvent;
import br.com.gestrest.pagamento.service.domain.model.event.PagamentoPendenteEvent;

public interface PagamentoEventPublisherPort {
    void publicarAprovado(PagamentoAprovadoEvent event);
    void publicarPendente(PagamentoPendenteEvent event);
}
