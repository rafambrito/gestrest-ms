package br.com.gestrest.restaurante.service.application.usecase.impl;

import org.springframework.stereotype.Component;

import br.com.gestrest.restaurante.service.domain.model.event.PedidoCriadoEvent;
import br.com.gestrest.restaurante.service.domain.model.ports.in.PedidoEventConsumerPort;

@Component
public class PedidoEventHandlerUseCaseImpl implements PedidoEventConsumerPort {

    @Override
    public void onPedidoCriado(PedidoCriadoEvent event) {
        System.out.println("Restaurante " + event.restauranteId() + ": Novo pedido " + event.pedidoId());
    }
}