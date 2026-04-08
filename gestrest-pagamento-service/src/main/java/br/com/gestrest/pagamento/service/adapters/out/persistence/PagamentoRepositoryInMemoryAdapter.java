package br.com.gestrest.pagamento.service.adapters.out.persistence;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import br.com.gestrest.pagamento.service.domain.model.Pagamento;
import br.com.gestrest.pagamento.service.domain.ports.out.PagamentoRepositoryPort;

@Component
public class PagamentoRepositoryInMemoryAdapter implements PagamentoRepositoryPort {

    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        return Pagamento.existente(
                sequence.getAndIncrement(),
                pagamento.getPedidoId(),
                pagamento.getUsuarioId(),
                pagamento.getValor(),
                pagamento.getStatus(),
                pagamento.getDataCriacao());
    }
}
