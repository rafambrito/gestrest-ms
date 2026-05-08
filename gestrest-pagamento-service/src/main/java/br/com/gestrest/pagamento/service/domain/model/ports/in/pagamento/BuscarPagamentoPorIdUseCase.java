package br.com.gestrest.pagamento.service.domain.model.ports.in.pagamento;

import br.com.gestrest.pagamento.service.domain.model.Pagamento;

public interface BuscarPagamentoPorIdUseCase {
  Pagamento buscarPorId(Long id);
}
