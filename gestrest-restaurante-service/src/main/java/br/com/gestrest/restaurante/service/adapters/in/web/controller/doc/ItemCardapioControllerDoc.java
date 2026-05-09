package br.com.gestrest.restaurante.service.adapters.in.web.controller.doc;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.AtualizarItemCardapioRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.request.CriarItemCardapioRequest;
import br.com.gestrest.restaurante.service.adapters.in.web.dto.response.ItemCardapioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Itens de Cardapio", description = "API para gestão de itens de cardapio")
public interface ItemCardapioControllerDoc {

    @Operation(summary = "Criar item de cardapio", description = "Cria um novo item de cardapio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemCardapioResponse.class), examples = @ExampleObject(value = "{\"id\":1,\"nome\":\"Pizza Marguerita\",\"descricao\":\"Pizza com mussarela e manjericao\",\"preco\":49.90,\"ativo\":true,\"restauranteId\":1}"))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos"),
            @ApiResponse(responseCode = "404", description = "Restaurante nao encontrado")
    })
    ResponseEntity<ItemCardapioResponse> criar(
            @Valid @RequestBody(description = "Dados do item", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CriarItemCardapioRequest.class), examples = @ExampleObject(value = "{\"nome\":\"Pizza Marguerita\",\"descricao\":\"Pizza com mussarela e manjericao\",\"preco\":49.90,\"restauranteId\":1}"))) CriarItemCardapioRequest request);

    @Operation(summary = "Buscar item por id", description = "Busca um item de cardapio pelo identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado"),
            @ApiResponse(responseCode = "404", description = "Item nao encontrado")
    })
    ResponseEntity<ItemCardapioResponse> buscarPorId(Long id);

    @Operation(summary = "Listar itens por restaurante", description = "Lista os itens de cardapio do restaurante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Restaurante nao encontrado")
    })
    ResponseEntity<List<ItemCardapioResponse>> listarPorRestaurante(Long restauranteId);

    @Operation(summary = "Atualizar item de cardapio", description = "Atualiza os dados de um item de cardapio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item atualizado"),
            @ApiResponse(responseCode = "404", description = "Item ou restaurante nao encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos")
    })
    ResponseEntity<ItemCardapioResponse> atualizar(
            Long id,
            @Valid @RequestBody(description = "Dados do item", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtualizarItemCardapioRequest.class), examples = @ExampleObject(value = "{\"nome\":\"Pizza Marguerita G\",\"descricao\":\"Pizza grande\",\"preco\":59.90,\"ativo\":true,\"restauranteId\":1}"))) AtualizarItemCardapioRequest request);

    @Operation(summary = "Excluir item de cardapio", description = "Exclui um item de cardapio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item excluido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item nao encontrado")
    })
    ResponseEntity<Void> excluir(Long id);
}
