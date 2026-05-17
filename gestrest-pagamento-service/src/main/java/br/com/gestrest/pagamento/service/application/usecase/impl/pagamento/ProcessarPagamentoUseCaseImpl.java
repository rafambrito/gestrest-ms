package br.com.gestrest.pagamento.service.application.usecase.impl.pagamento;

import java.time.LocalDateTime;

import br.com.gestrest.pagamento.service.application.usecase.command.pagamento.ProcessarPagamentoCommand;
import br.com.gestrest.pagamento.service.domain.model.Pagamento;
import br.com.gestrest.pagamento.service.domain.model.event.PagamentoAprovadoEvent;
import br.com.gestrest.pagamento.service.domain.model.event.PagamentoPendenteEvent;
import br.com.gestrest.pagamento.service.domain.model.event.PedidoCriadoEvent;
import br.com.gestrest.pagamento.service.domain.model.ports.in.PedidoEventConsumerPort;
import br.com.gestrest.pagamento.service.domain.model.ports.in.pagamento.ProcessarPagamentoUseCase;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoEventPublisherPort;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoGatewayPort;
import br.com.gestrest.pagamento.service.domain.model.ports.out.PagamentoRepositoryPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessarPagamentoUseCaseImpl implements ProcessarPagamentoUseCase, PedidoEventConsumerPort {

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
        pagamento = pagamentoRepository.salvar(pagamento);
        log.info("Pagamento inicial salvo. pagamentoId={}, status={}", pagamento.getId(), pagamento.getStatus());

        try {
            var gatewayResponse = pagamentoGateway.iniciarPagamento(pagamento.getId(), pagamento.getPedidoId(), pagamento.getUsuarioId(), pagamento.getValor());
            pagamento.associarPagamentoExterno(gatewayResponse.pagamentoIdExterno());
            log.debug("Pagamento iniciado no gateway externo. pagamentoId={}, pagamentoIdExterno={}", pagamento.getId(), gatewayResponse.pagamentoIdExterno());

            var status = gatewayResponse.status();
            if ("sucesso".equalsIgnoreCase(status)) {
                pagamento.aprovar();
                log.info("Pagamento aprovado. pagamentoId={}, pedidoId={}", pagamento.getId(), pagamento.getPedidoId());
                pagamentoEventPublisher.publicarAprovado(new PagamentoAprovadoEvent(
                    pagamento.getPedidoId(),
                    pagamento.getUsuarioId(),
                    LocalDateTime.now()
                ));
            } else if ("PENDENTE".equalsIgnoreCase(status)) {
                pagamento.marcarPendente();
                log.info("Pagamento pendente. pagamentoId={}, pedidoId={}", pagamento.getId(), pagamento.getPedidoId());
                pagamentoEventPublisher.publicarPendente(new PagamentoPendenteEvent(
                    pagamento.getPedidoId(),
                    pagamento.getUsuarioId(),
                    LocalDateTime.now()
                ));
            } else {
                status = pagamentoGateway.consultarStatus(gatewayResponse.pagamentoIdExterno());
                if ("sucesso".equalsIgnoreCase(status)) {
                    pagamento.aprovar();
                    log.info("Pagamento aprovado após consulta de status. pagamentoId={}, pedidoId={}", pagamento.getId(), pagamento.getPedidoId());
                    pagamentoEventPublisher.publicarAprovado(new PagamentoAprovadoEvent(
                        pagamento.getPedidoId(),
                        pagamento.getUsuarioId(),
                        LocalDateTime.now()
                    ));
                } else {
                    pagamento.marcarPendente();
                    log.info("Pagamento pendente. pagamentoId={}, pedidoId={}", pagamento.getId(), pagamento.getPedidoId());
                    pagamentoEventPublisher.publicarPendente(new PagamentoPendenteEvent(
                        pagamento.getPedidoId(),
                        pagamento.getUsuarioId(),
                        LocalDateTime.now()
                    ));
                }
            }
        } catch (RuntimeException ex) {
            log.error("Erro ao processar pagamento. pagamentoId={}, pedidoId={}", pagamento.getId(), pagamento.getPedidoId(), ex);
            pagamento.marcarPendente();
            log.warn("Pagamento marcado como pendente devido a erro no gateway. pagamentoId={}, pedidoId={}", pagamento.getId(), pagamento.getPedidoId());
            
            pagamentoEventPublisher.publicarPendente(new PagamentoPendenteEvent(
                pagamento.getPedidoId(),
                pagamento.getUsuarioId(),
                LocalDateTime.now()
            ));
        }
        return pagamentoRepository.salvar(pagamento);
    }

    @Override
    public void onPedidoCriado(PedidoCriadoEvent event) {
        var command = new ProcessarPagamentoCommand(event.pedidoId(), event.usuarioId(), event.valor());
        log.info("Processamento de pagamento iniciado via evento PedidoCriado para pedidoId={}", event.pedidoId());
        processar(command);        
    }
}
