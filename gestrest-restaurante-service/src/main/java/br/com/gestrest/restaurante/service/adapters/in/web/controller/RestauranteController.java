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
import org.springframework.web.bind.annotation.RestController;

import br.com.gestrest.restaurante.service.adapters.in.web.controller.doc.RestauranteControllerDoc;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.AtualizarRestauranteRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.CriarRestauranteRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.response.RestauranteResponse;
import br.com.gestrest.restaurante.service.adapters.in.web.mapper.RestauranteWebMapper;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.AtualizarRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.BuscarRestaurantePorIdUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.CriarRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.ExcluirRestauranteUseCase;
import br.com.gestrest.restaurante.service.domain.model.ports.in.restaurante.ListarRestaurantesUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/restaurantes")
@RequiredArgsConstructor
public class RestauranteController implements RestauranteControllerDoc {

    private final CriarRestauranteUseCase criarUseCase;
    private final BuscarRestaurantePorIdUseCase buscarPorIdUseCase;
    private final ListarRestaurantesUseCase listarUseCase;
    private final AtualizarRestauranteUseCase atualizarUseCase;
    private final ExcluirRestauranteUseCase excluirUseCase;
    private final RestauranteWebMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<RestauranteResponse> criar(@Valid @RequestBody CriarRestauranteRequest request) {
        var restaurante = criarUseCase.criar(mapper.toCommand(request));
        var response = mapper.toResponse(restaurante);
        return ResponseEntity.created(URI.create("/api/v1/restaurantes/" + response.id())).body(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponse> buscarPorId(@PathVariable Long id) {
        var restaurante = buscarPorIdUseCase.buscar(id);
        return ResponseEntity.ok(mapper.toResponse(restaurante));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RestauranteResponse>> listar() {
        var response = listarUseCase.listar().stream().map(mapper::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarRestauranteRequest request) {
        var restaurante = atualizarUseCase.atualizar(id, mapper.toCommand(request));
        return ResponseEntity.ok(mapper.toResponse(restaurante));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirUseCase.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
