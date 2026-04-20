package br.com.gestrest.pedido.service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gestrest.pedido.service.application.usecase.impl.itemcardapio.AtualizarItemCardapioUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.itemcardapio.BuscarItemCardapioPorIdUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.itemcardapio.CriarItemCardapioUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.itemcardapio.ExcluirItemCardapioUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.itemcardapio.ListarItensPorRestauranteUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.pedido.BuscarPedidoPorIdUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.pedido.CriarPedidoUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.pedido.ListarPedidosPorUsuarioUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.restaurante.AtualizarRestauranteUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.restaurante.BuscarRestaurantePorIdUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.restaurante.CriarRestauranteUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.restaurante.ExcluirRestauranteUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.restaurante.ListarRestauranteUseCaseImpl;
import br.com.gestrest.pedido.service.application.usecase.impl.restaurante.ValidarDonoRestauranteService;
import br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio.AtualizarItemCardapioUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio.BuscarItemCardapioPorIdUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio.CriarItemCardapioUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio.ExcluirItemCardapioUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.itemcardapio.ListarItensPorRestauranteUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.BuscarPedidoPorIdUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.CriarPedidoUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.ListarPedidosPorUsuarioUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.restaurante.AtualizarRestauranteUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.restaurante.CriarRestauranteUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.restaurante.ExcluirRestauranteUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.restaurante.ListarRestauranteUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.out.DonoRestauranteValidatorPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.ItemCardapioRepositoryPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PagamentoEventPublisherPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PagamentoProcessadorPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoEventPublisherPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoRepositoryPort;
import br.com.gestrest.pedido.service.domain.model.ports.out.RestauranteRepositoryPort;

@Configuration
public class UseCaseConfig {

	@Bean
	public ValidarDonoRestauranteService validarDonoRestauranteService(
			DonoRestauranteValidatorPort donoRestauranteValidatorPort) {
		return new ValidarDonoRestauranteService(donoRestauranteValidatorPort);
	}

	@Bean
	public CriarRestauranteUseCase criarRestauranteUseCase(
			RestauranteRepositoryPort repository,
			ValidarDonoRestauranteService validarDonoRestauranteService) {
		return new CriarRestauranteUseCaseImpl(repository, validarDonoRestauranteService);
	}

	@Bean
	public AtualizarRestauranteUseCase atualizarRestauranteUseCase(
			RestauranteRepositoryPort repository,
			ValidarDonoRestauranteService validarDonoRestauranteService) {
		return new AtualizarRestauranteUseCaseImpl(repository, validarDonoRestauranteService);
	}

	@Bean
	public BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase(RestauranteRepositoryPort repository) {
		return new BuscarRestaurantePorIdUseCaseImpl(repository);
	}

	@Bean
	public ListarRestauranteUseCase listarRestauranteUseCase(RestauranteRepositoryPort repository) {
		return new ListarRestauranteUseCaseImpl(repository);
	}

	@Bean
	public ExcluirRestauranteUseCase excluirRestauranteUseCase(
			RestauranteRepositoryPort repository,
			ItemCardapioRepositoryPort itemRepository) {
		return new ExcluirRestauranteUseCaseImpl(repository, itemRepository);
	}

	@Bean
	public CriarItemCardapioUseCase criarItemCardapioUseCase(
			ItemCardapioRepositoryPort repository,
			RestauranteRepositoryPort restauranteRepository) {
		return new CriarItemCardapioUseCaseImpl(repository, restauranteRepository);
	}

	@Bean
	public AtualizarItemCardapioUseCase atualizarItemCardapioUseCase(ItemCardapioRepositoryPort repository) {
		return new AtualizarItemCardapioUseCaseImpl(repository);
	}

	@Bean
	public BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase(ItemCardapioRepositoryPort repository) {
		return new BuscarItemCardapioPorIdUseCaseImpl(repository);
	}

	@Bean
	public ListarItensPorRestauranteUseCase listarItensPorRestauranteUseCase(ItemCardapioRepositoryPort repository) {
		return new ListarItensPorRestauranteUseCaseImpl(repository);
	}

	@Bean
	public ExcluirItemCardapioUseCase excluirItemCardapioUseCase(ItemCardapioRepositoryPort repository) {
		return new ExcluirItemCardapioUseCaseImpl(repository);
	}

	@Bean
	public CriarPedidoUseCase criarPedidoUseCase(
			PedidoRepositoryPort pedidoRepository,
			PagamentoProcessadorPort pagamentoProcessador,
			PedidoEventPublisherPort pedidoEventPublisher,
			PagamentoEventPublisherPort pagamentoEventPublisher) {
		return new CriarPedidoUseCaseImpl(
				pedidoRepository,
				pagamentoProcessador,
				pedidoEventPublisher,
				pagamentoEventPublisher);
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