package br.com.gestrest.pedido.service.adapters.in.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
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

    @Override
    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@Valid @RequestBody CriarPedidoRequest request) {
        var criado = criarPedidoUseCase.criar(pedidoWebMapper.toCommand(request));
        var response = pedidoWebMapper.toResponse(criado);
        return ResponseEntity.created(URI.create("/api/v1/pedidos/" + response.id())).body(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoWebMapper.toResponse(buscarPedidoPorIdUseCase.buscar(id)));
    }

    @Override
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(listarPedidosPorUsuarioUseCase.listar(usuarioId).stream().map(pedidoWebMapper::toResponse).toList());
    }
}
