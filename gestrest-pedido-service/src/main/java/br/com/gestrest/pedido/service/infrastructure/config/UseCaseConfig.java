package br.com.gestrest.pedido.service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gestrest.pedido.service.application.usecase.impl.pedido.BuscarPedidoPorIdUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.pedido.CriarPedidoUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.pedido.ListarPedidosPorUsuarioUseCaseImpl;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.BuscarPedidoPorIdUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.CriarPedidoUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.ListarPedidosPorUsuarioUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.PagamentoClientPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PagamentoEventPublisherPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoEventPublisherPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoRepositoryPort;

@Configuration
public class UseCaseConfig {

	@Bean
	    public CriarPedidoUseCase criarPedidoUseCase(
		    PedidoRepositoryPort pedidoRepository,
		    PedidoEventPublisherPort pedidoEventPublisher) {
		return new CriarPedidoUseCaseImpl(
			pedidoRepository,
			pedidoEventPublisher);
	    }

	@Bean
	public BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase(PedidoRepositoryPort pedidoRepository) {
		return new BuscarPedidoPorIdUseCaseImpl(pedidoRepository);
	}

	@Bean
	public ListarPedidosPorUsuarioUseCase listarPedidosPorUsuarioUseCase(PedidoRepositoryPort pedidoRepository) {
		return new ListarPedidosPorUsuarioUseCaseImpl(pedidoRepository);
	}
}