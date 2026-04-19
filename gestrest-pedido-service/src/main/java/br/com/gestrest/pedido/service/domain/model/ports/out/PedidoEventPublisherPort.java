package br.com.gestrest.pedido.service.domain.model.ports.out;

import br.com.gestrest.pedido.service.domain.model.event.PedidoCriadoEvent;

public interface PedidoEventPublisherPort {
    void publicarPedidoCriado(PedidoCriadoEvent event);
}
