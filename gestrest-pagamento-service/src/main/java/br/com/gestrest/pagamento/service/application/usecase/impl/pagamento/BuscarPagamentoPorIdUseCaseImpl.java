package br.com.gestrest.pagamento.service.application.usecase.impl.pagamento;

import br.com.gestrest.pagamento.service.domain.exception.PagamentoNaoEncontradoException;
import br.com.gestrest.pagamento.service.domain.model.Pagamento;
import br.com.gestrest.pagamento.service.domain.model.ports.in.pagamento.BuscarPagamentoPorIdUseCase;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoRepositoryPort;

public class BuscarPagamentoPorIdUseCaseImpl implements BuscarPagamentoPorIdUseCase {

  private final PagamentoRepositoryPort pagamentoRepositoryPort;

  public BuscarPagamentoPorIdUseCaseImpl(PagamentoRepositoryPort pagamentoRepositoryPort) {
    this.pagamentoRepositoryPort = pagamentoRepositoryPort;
  }

  @Override
  public Pagamento buscarPorId(Long id) {
    return pagamentoRepositoryPort.buscarPorId(id).orElseThrow(() -> new PagamentoNaoEncontradoException(id));
  }
}