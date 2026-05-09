package br.com.gestrest.restaurante.service.adapters.in.web.mapper;

import org.springframework.stereotype.Component;

import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.AtualizarItemCardapioRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.CriarItemCardapioRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.response.ItemCardapioResponse;
import br.com.gestrest.restaurante.service.application.usecase.command.itemcardapio.AtualizarItemCardapioCommand;
import br.com.gestrest.restaurante.service.application.usecase.command.itemcardapio.CriarItemCardapioCommand;
import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;

@Component
public class ItemCardapioWebMapper {

    public CriarItemCardapioCommand toCommand(CriarItemCardapioRequest request) {
        return new CriarItemCardapioCommand(
                request.nome(),
                request.descricao(),
                request.preco(),
                request.restauranteId());
    }

    public AtualizarItemCardapioCommand toCommand(AtualizarItemCardapioRequest request) {
        return new AtualizarItemCardapioCommand(
                request.nome(),
                request.descricao(),
                request.preco(),
                request.ativo(),
                request.restauranteId());
    }

    public ItemCardapioResponse toResponse(ItemCardapio itemCardapio) {
        return new ItemCardapioResponse(
                itemCardapio.getId(),
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.isAtivo(),
                itemCardapio.getRestauranteId());
    }
}
