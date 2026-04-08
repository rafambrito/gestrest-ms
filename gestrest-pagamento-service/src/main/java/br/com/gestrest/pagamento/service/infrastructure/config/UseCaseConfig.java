package br.com.gestrest.pagamento.service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gestrest.pagamento.service.application.usecase.impl.pagamento.ProcessarPagamentoUseCaseImpl;
import br.com.gestrest.pagamento.service.domain.ports.in.pagamento.ProcessarPagamentoUseCase;
import br.com.gestrest.pagamento.service.domain.ports.out.PagamentoEventPublisherPort;
import br.com.gestrest.pagamento.service.domain.ports.out.PagamentoGatewayPort;
import br.com.gestrest.pagamento.service.domain.ports.out.PagamentoRepositoryPort;

@Configuration
public class UseCaseConfig {

    @Bean
    public ProcessarPagamentoUseCase processarPagamentoUseCase(
            PagamentoRepositoryPort pagamentoRepository,
            PagamentoGatewayPort pagamentoGateway,
            PagamentoEventPublisherPort pagamentoEventPublisher) {
        return new ProcessarPagamentoUseCaseImpl(pagamentoRepository, pagamentoGateway, pagamentoEventPublisher);
    }
}
