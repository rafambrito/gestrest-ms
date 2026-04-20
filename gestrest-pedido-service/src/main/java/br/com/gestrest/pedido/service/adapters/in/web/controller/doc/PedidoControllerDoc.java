package br.com.gestrest.pedido.service.adapters.in.web.controller.doc;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.gestrest.pedido.service.adapters.in.web.dto.request.CriarPedidoRequest;
import br.com.gestrest.pedido.service.adapters.in.web.dto.response.PedidoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pedidos", description = "API para gerenciamento de pedidos")
public interface PedidoControllerDoc {

    @Operation(summary = "Criar pedido", description = "Cria um pedido, calcula total e tenta processar pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos")
    })
    ResponseEntity<PedidoResponse> criar(
            @Valid @RequestBody(required = true, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"usuarioId\":10,\"restauranteId\":2,\"itens\":[{\"itemCardapioId\":5,\"nomeItem\":\"Pizza\",\"quantidade\":2,\"precoUnitario\":49.90}]}"))) CriarPedidoRequest request);

    @Operation(summary = "Buscar pedido por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pedido nao encontrado")
    })
    ResponseEntity<PedidoResponse> buscar(@PathVariable Long id);

    @Operation(summary = "Listar pedidos por usuario")
    @ApiResponse(responseCode = "200", description = "Pedidos do usuario")
    ResponseEntity<List<PedidoResponse>> listarPorUsuario(@PathVariable Long usuarioId);
}
