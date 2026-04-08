package br.com.gestrest.pagamento.service.adapters.in.web.controller.doc;

import org.springframework.http.ResponseEntity;

import br.com.gestrest.pagamento.service.adapters.in.web.dto.request.ProcessarPagamentoRequest;
import br.com.gestrest.pagamento.service.adapters.in.web.dto.response.PagamentoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Pagamentos", description = "API para processamento de pagamentos")
public interface PagamentoControllerDoc {

    @Operation(summary = "Processar pagamento", description = "Processa pagamento com estrategia de retry/timeout/fallback mock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento processado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagamentoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos")
    })
    ResponseEntity<PagamentoResponse> processar(
            @Valid @RequestBody(required = true, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"pedidoId\":100,\"usuarioId\":10,\"valor\":129.90}"))) ProcessarPagamentoRequest request);
}
