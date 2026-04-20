package br.com.gestrest.pagamento.service.domain.model.ports.in;

import br.com.gestrest.pagamento.service.domain.model.event.PedidoCriadoEvent;

public interface PedidoEventConsumerPort {

    void onPedidoCriado(PedidoCriadoEvent event);
}