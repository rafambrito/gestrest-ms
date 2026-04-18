package br.com.gestrest.pagamento.service.domain.model.ports.in.pagamento;

import br.com.gestrest.pagamento.service.application.usecase.command.pagamento.ProcessarPagamentoCommand;
import br.com.gestrest.pagamento.service.domain.model.Pagamento;

public interface ProcessarPagamentoUseCase {
    Pagamento processar(ProcessarPagamentoCommand command);
}
