package br.com.gestrest.pedido.service.adapters.out.event;

import org.springframework.stereotype.Component;

import br.com.gestrest.pedido.service.domain.model.event.PedidoCriadoEvent;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoEventPublisherPort;

@Component
public class MockPedidoEventPublisherAdapter implements PedidoEventPublisherPort {

    @Override
    public void publicarPedidoCriado(PedidoCriadoEvent event) {
        // Estrutura preparada para envio de evento pedido.criado.
    }
}
