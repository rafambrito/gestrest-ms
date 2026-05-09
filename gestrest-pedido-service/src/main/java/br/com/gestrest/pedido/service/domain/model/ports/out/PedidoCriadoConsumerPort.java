package br.com.gestrest.pedido.service.domain.model.ports.out;

import br.com.gestrest.pedido.service.domain.model.event.PedidoCriadoEvent;

public interface PedidoCriadoConsumerPort {
    void consumir(PedidoCriadoEvent event);
}
