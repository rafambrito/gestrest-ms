package br.com.gestrest.pedido.service.adapters.out.event;

import org.springframework.stereotype.Component;

import br.com.gestrest.pedido.service.domain.model.event.PedidoCriadoEvent;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoCriadoConsumerPort;

@Component
public class MockPedidoCriadoConsumerAdapter implements PedidoCriadoConsumerPort {

    @Override
    public void consumir(PedidoCriadoEvent event) {
        // Estrutura preparada para consumo de evento.
    }
}
