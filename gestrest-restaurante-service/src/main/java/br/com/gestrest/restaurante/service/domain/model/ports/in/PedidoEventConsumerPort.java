package br.com.gestrest.restaurante.service.domain.model.ports.in;

import br.com.gestrest.restaurante.service.domain.model.event.PedidoCriadoEvent;

public interface PedidoEventConsumerPort {

    void onPedidoCriado(PedidoCriadoEvent event);
}