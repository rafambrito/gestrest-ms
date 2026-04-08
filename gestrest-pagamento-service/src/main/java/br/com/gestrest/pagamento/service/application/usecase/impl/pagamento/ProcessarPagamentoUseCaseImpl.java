package br.com.gestrest.pagamento.service.application.usecase.impl.pagamento;

import br.com.gestrest.pagamento.service.application.usecase.command.pagamento.ProcessarPagamentoCommand;
import br.com.gestrest.pagamento.service.domain.model.Pagamento;
import br.com.gestrest.pagamento.service.domain.model.event.PagamentoAprovadoEvent;
import br.com.gestrest.pagamento.service.domain.model.event.PagamentoPendenteEvent;
import br.com.gestrest.pagamento.service.domain.ports.in.pagamento.ProcessarPagamentoUseCase;
import br.com.gestrest.pagamento.service.domain.ports.out.PagamentoEventPublisherPort;
import br.com.gestrest.pagamento.service.domain.ports.out.PagamentoGatewayPort;
import br.com.gestrest.pagamento.service.domain.ports.out.PagamentoRepositoryPort;

public class ProcessarPagamentoUseCaseImpl implements ProcessarPagamentoUseCase {

    private final PagamentoRepositoryPort pagamentoRepository;
    private final PagamentoGatewayPort pagamentoGateway;
    private final PagamentoEventPublisherPort pagamentoEventPublisher;

    public ProcessarPagamentoUseCaseImpl(PagamentoRepositoryPort pagamentoRepository,
            PagamentoGatewayPort pagamentoGateway,
            PagamentoEventPublisherPort pagamentoEventPublisher) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoGateway = pagamentoGateway;
        this.pagamentoEventPublisher = pagamentoEventPublisher;
    }

    @Override
    public Pagamento processar(ProcessarPagamentoCommand command) {
        var pagamento = Pagamento.criar(command.pedidoId(), command.usuarioId(), command.valor());
        boolean aprovado;
        try {
            aprovado = pagamentoGateway.cobrar(command.pedidoId(), command.usuarioId(), command.valor());
        } catch (RuntimeException ex) {
            aprovado = false;
        }
        if (aprovado) {
            pagamento.aprovar();
            pagamentoEventPublisher.publicarAprovado(new PagamentoAprovadoEvent(pagamento.getPedidoId(), pagamento.getUsuarioId()));
        } else {
            pagamento.marcarPendente();
            pagamentoEventPublisher.publicarPendente(new PagamentoPendenteEvent(pagamento.getPedidoId(), pagamento.getUsuarioId()));
        }
        return pagamentoRepository.salvar(pagamento);
    }
}
