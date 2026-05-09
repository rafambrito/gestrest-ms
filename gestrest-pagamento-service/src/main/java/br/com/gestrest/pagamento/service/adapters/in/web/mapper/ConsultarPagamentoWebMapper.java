package br.com.gestrest.pagamento.service.adapters.in.web.mapper;

import br.com.gestrest.pagamento.service.adapters.in.web.dto.response.PagamentoResponse;
import br.com.gestrest.pagamento.service.domain.model.Pagamento;

public class ConsultarPagamentoWebMapper {
  public PagamentoResponse toResponse(Pagamento pagamento) {
    return new PagamentoResponse(
                pagamento.getId(),
                pagamento.getPedidoId(),
                pagamento.getUsuarioId(),
                pagamento.getValor(),
                pagamento.getStatus(),
                pagamento.getDataCriacao()
    );
  }
}
