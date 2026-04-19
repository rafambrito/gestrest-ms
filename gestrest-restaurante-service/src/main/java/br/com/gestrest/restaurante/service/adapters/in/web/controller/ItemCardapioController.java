package br.com.gestrest.restaurante.service.adapters.in.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestrest.restaurante.service.adapters.in.web.controller.doc.ItemCardapioControllerDoc;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.AtualizarItemCardapioRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.CriarItemCardapioRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.response.ItemCardapioResponse;
import br.com.gestrest.restaurante.service.adapters.in.web.mapper.ItemCardapioWebMapper;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.AtualizarItemCardapioUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.BuscarItemCardapioPorIdUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.CriarItemCardapioUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.ExcluirItemCardapioUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.itemcardapio.ListarItensPorRestauranteUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/itens-cardapio")
@RequiredArgsConstructor
public class ItemCardapioController implements ItemCardapioControllerDoc {

    private final CriarItemCardapioUseCase criarUseCase;
    private final BuscarItemCardapioPorIdUseCase buscarPorIdUseCase;
    private final ListarItensPorRestauranteUseCase listarPorRestauranteUseCase;
    private final AtualizarItemCardapioUseCase atualizarUseCase;
    private final ExcluirItemCardapioUseCase excluirUseCase;
    private final ItemCardapioWebMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<ItemCardapioResponse> criar(@Valid @RequestBody CriarItemCardapioRequest request) {
        var item = criarUseCase.criar(mapper.toCommand(request));
        var response = mapper.toResponse(item);
        return ResponseEntity.created(URI.create("/api/v1/itens-cardapio/" + response.id())).body(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapioResponse> buscarPorId(@PathVariable Long id) {
        var item = buscarPorIdUseCase.buscar(id);
        return ResponseEntity.ok(mapper.toResponse(item));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ItemCardapioResponse>> listarPorRestaurante(@RequestParam Long restauranteId) {
        var response = listarPorRestauranteUseCase.listar(restauranteId).stream().map(mapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarItemCardapioRequest request) {
        var item = atualizarUseCase.atualizar(id, mapper.toCommand(request));
        return ResponseEntity.ok(mapper.toResponse(item));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirUseCase.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
