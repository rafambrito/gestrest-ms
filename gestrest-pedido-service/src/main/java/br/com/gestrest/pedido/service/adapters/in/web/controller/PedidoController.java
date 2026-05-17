package br.com.gestrest.pedido.service.adapters.in.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestrest.pedido.service.adapters.in.web.controller.doc.PedidoControllerDoc;
import br.com.gestrest.pedido.service.adapters.in.web.dto.request.CriarPedidoRequest;
import br.com.gestrest.pedido.service.adapters.in.web.dto.response.PedidoResponse;
import br.com.gestrest.pedido.service.adapters.in.web.mapper.PedidoWebMapper;
import br.com.gestrest.pedido.service.adapters.in.web.security.AuthenticatedUserExtractor;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.BuscarPedidoPorIdUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.CriarPedidoUseCase;
import br.com.gestrest.pedido.service.domain.model.ports.in.pedido.ListarPedidosPorUsuarioUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController implements PedidoControllerDoc {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase;
    private final ListarPedidosPorUsuarioUseCase listarPedidosPorUsuarioUseCase;
    private final PedidoWebMapper pedidoWebMapper;
    private final AuthenticatedUserExtractor authenticatedUserExtractor;

    @Override
    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@Valid @RequestBody CriarPedidoRequest request) {
        Long usuarioId = authenticatedUserExtractor.getAuthenticatedUserId();
        var criado = criarPedidoUseCase.criar(pedidoWebMapper.toCommand(request, usuarioId));
        var response = pedidoWebMapper.toResponse(criado);
        var uri = java.util.Objects.requireNonNull(URI.create("/api/v1/pedidos/" + response.id()));
        return ResponseEntity.created(uri).body(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscar(@PathVariable Long id) {
        var pedido = buscarPedidoPorIdUseCase.buscar(id);
        var response = pedidoWebMapper.toResponse(pedido);

        Long authenticatedUserId = authenticatedUserExtractor.getAuthenticatedUserId();
        if (!authenticatedUserExtractor.isAuthenticatedAdmin() && !authenticatedUserId.equals(response.usuarioId())) {
            throw new AccessDeniedException("Acesso negado ao pedido requisitado");
        }

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        Long authenticatedUserId = authenticatedUserExtractor.getAuthenticatedUserId();
        if (!authenticatedUserExtractor.isAuthenticatedAdmin() && !authenticatedUserId.equals(usuarioId)) {
            throw new AccessDeniedException("Acesso negado aos pedidos deste usuario");
        }

        return ResponseEntity.ok(listarPedidosPorUsuarioUseCase.listar(usuarioId).stream().map(pedidoWebMapper::toResponse).toList());
    }
}
