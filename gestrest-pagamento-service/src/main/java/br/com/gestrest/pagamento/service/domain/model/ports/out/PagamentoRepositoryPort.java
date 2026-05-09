package br.com.gestrest.pagamento.service.domain.model.ports.out;

import java.util.Optional;

import br.com.gestrest.pagamento.service.domain.model.Pagamento;

public interface PagamentoRepositoryPort {
    Pagamento salvar(Pagamento pagamento);

    Optional<Pagamento> buscarPorId(Long id);
}
