package br.com.gestrest.restaurante.service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio.AtualizarItemCardapioUseCaseImpl;
import br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio.BuscarItemCardapioPorIdUseCaseImpl;
import br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio.CriarItemCardapioUseCaseImpl;
import br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio.ExcluirItemCardapioUseCaseImpl;
import br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio.ListarItensPorRestauranteUseCaseImpl;
import br.com.gestrest.restaurante.service.application.usecase.impl.restaurante.AtualizarRestauranteUseCaseImpl;
import br.com.gestrest.restaurante.service.application.usecase.impl.restaurante.BuscarRestaurantePorIdUseCaseImpl;
import br.com.gestrest.restaurante.service.application.usecase.impl.restaurante.CriarRestauranteUseCaseImpl;
import br.com.gestrest.restaurante.service.application.usecase.impl.restaurante.ExcluirRestauranteUseCaseImpl;
import br.com.gestrest.restaurante.service.application.usecase.impl.restaurante.ListarRestaurantesUseCaseImpl;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.AtualizarItemCardapioUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.BuscarItemCardapioPorIdUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.CriarItemCardapioUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.ExcluirItemCardapioUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.ListarItensPorRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.AtualizarRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.CriarRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.ExcluirRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.ListarRestaurantesUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortRead;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortWrite;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortRead;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortWrite;

@Configuration
public class UseCaseConfig {

    @Bean
    public CriarRestauranteUseCase criarRestauranteUseCase(RestauranteRepositoryPortWrite restauranteRepositoryWrite) {
        return new CriarRestauranteUseCaseImpl(restauranteRepositoryWrite);
    }

    @Bean
    public BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase(RestauranteRepositoryPortRead restauranteRepositoryRead) {
        return new BuscarRestaurantePorIdUseCaseImpl(restauranteRepositoryRead);
    }

    @Bean
    public ListarRestaurantesUseCase listarRestaurantesUseCase(RestauranteRepositoryPortRead restauranteRepositoryRead) {
        return new ListarRestaurantesUseCaseImpl(restauranteRepositoryRead);
    }

    @Bean
    public AtualizarRestauranteUseCase atualizarRestauranteUseCase(
            RestauranteRepositoryPortRead restauranteRepositoryRead,
            RestauranteRepositoryPortWrite restauranteRepositoryWrite) {
        return new AtualizarRestauranteUseCaseImpl(restauranteRepositoryRead, restauranteRepositoryWrite);
    }

    @Bean
    public ExcluirRestauranteUseCase excluirRestauranteUseCase(
            RestauranteRepositoryPortRead restauranteRepositoryRead,
            RestauranteRepositoryPortWrite restauranteRepositoryWrite,
            ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite) {
        return new ExcluirRestauranteUseCaseImpl(
                restauranteRepositoryRead,
                restauranteRepositoryWrite,
                itemCardapioRepositoryWrite);
    }

    @Bean
    public CriarItemCardapioUseCase criarItemCardapioUseCase(
            ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite,
            RestauranteRepositoryPortRead restauranteRepositoryRead) {
        return new CriarItemCardapioUseCaseImpl(itemCardapioRepositoryWrite, restauranteRepositoryRead);
    }

    @Bean
    public BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase(
            ItemCardapioRepositoryPortRead itemCardapioRepositoryRead) {
        return new BuscarItemCardapioPorIdUseCaseImpl(itemCardapioRepositoryRead);
    }

    @Bean
    public ListarItensPorRestauranteUseCase listarItensPorRestauranteUseCase(
            ItemCardapioRepositoryPortRead itemCardapioRepositoryRead,
            RestauranteRepositoryPortRead restauranteRepositoryRead) {
        return new ListarItensPorRestauranteUseCaseImpl(itemCardapioRepositoryRead, restauranteRepositoryRead);
    }

    @Bean
    public AtualizarItemCardapioUseCase atualizarItemCardapioUseCase(
            ItemCardapioRepositoryPortRead itemCardapioRepositoryRead,
            ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite,
            RestauranteRepositoryPortRead restauranteRepositoryRead) {
        return new AtualizarItemCardapioUseCaseImpl(
                itemCardapioRepositoryRead,
                itemCardapioRepositoryWrite,
                restauranteRepositoryRead);
    }

    @Bean
    public ExcluirItemCardapioUseCase excluirItemCardapioUseCase(
            ItemCardapioRepositoryPortRead itemCardapioRepositoryRead,
            ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite) {
        return new ExcluirItemCardapioUseCaseImpl(itemCardapioRepositoryRead, itemCardapioRepositoryWrite);
    }
}
