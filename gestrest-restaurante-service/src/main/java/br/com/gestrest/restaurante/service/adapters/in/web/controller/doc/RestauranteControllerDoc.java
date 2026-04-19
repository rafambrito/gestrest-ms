package br.com.gestrest.restaurante.service.adapters.in.web.controller.doc;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.AtualizarRestauranteRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.CriarRestauranteRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.response.RestauranteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Restaurantes", description = "API para gestão de restaurantes")
public interface RestauranteControllerDoc {

    @Operation(summary = "Criar restaurante", description = "Cria um novo restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurante criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestauranteResponse.class), examples = @ExampleObject(value = "{\"id\":1,\"nome\":\"Restaurante Central\",\"ativo\":true}"))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos")
    })
    ResponseEntity<RestauranteResponse> criar(
            @Valid @RequestBody(description = "Dados do restaurante", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CriarRestauranteRequest.class), examples = @ExampleObject(value = "{\"nome\":\"Restaurante Central\"}"))) CriarRestauranteRequest request);

    @Operation(summary = "Buscar restaurante por id", description = "Busca um restaurante pelo identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurante encontrado"),
            @ApiResponse(responseCode = "404", description = "Restaurante nao encontrado")
    })
    ResponseEntity<RestauranteResponse> buscarPorId(Long id);

    @Operation(summary = "Listar restaurantes", description = "Lista todos os restaurantes")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    ResponseEntity<List<RestauranteResponse>> listar();

    @Operation(summary = "Atualizar restaurante", description = "Atualiza os dados de um restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurante atualizado"),
            @ApiResponse(responseCode = "404", description = "Restaurante nao encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos")
    })
    ResponseEntity<RestauranteResponse> atualizar(
            Long id,
            @Valid @RequestBody(description = "Dados do restaurante", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtualizarRestauranteRequest.class), examples = @ExampleObject(value = "{\"nome\":\"Restaurante Central\",\"ativo\":true}"))) AtualizarRestauranteRequest request);

    @Operation(summary = "Excluir restaurante", description = "Exclui um restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurante excluido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante nao encontrado")
    })
    ResponseEntity<Void> excluir(Long id);
}
