package br.com.gestrest.restaurante.service.adapters.in.web.mapper;

import org.springframework.stereotype.Component;

import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.AtualizarRestauranteRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.CriarRestauranteRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.response.RestauranteResponse;
import br.com.gestrest.restaurante.service.application.usecase.command.restaurante.AtualizarRestauranteCommand;
import br.com.gestrest.restaurante.service.application.usecase.command.restaurante.CriarRestauranteCommand;
import br.com.gestrest.restaurante.service.domain.model.Restaurante;

@Component
public class RestauranteWebMapper {

    public CriarRestauranteCommand toCommand(CriarRestauranteRequest request) {
        return new CriarRestauranteCommand(request.nome());
    }

    public AtualizarRestauranteCommand toCommand(AtualizarRestauranteRequest request) {
        return new AtualizarRestauranteCommand(request.nome(), request.ativo());
    }

    public RestauranteResponse toResponse(Restaurante restaurante) {
        return new RestauranteResponse(restaurante.getId(), restaurante.getNome(), restaurante.isAtivo());
    }
}
