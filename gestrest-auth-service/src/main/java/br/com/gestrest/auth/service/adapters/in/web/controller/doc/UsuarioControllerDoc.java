package br.com.gestrest.auth.service.adapters.in.web.controller.doc;

import org.springframework.http.ResponseEntity;

import br.com.gestrest.auth.service.adapters.in.web.dto.request.CriarUsuarioRequest;
import br.com.gestrest.auth.service.adapters.in.web.dto.response.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuarios", description = "API para cadastro de usuários e perfis")
public interface UsuarioControllerDoc {

    @Operation(summary = "Criar usuario", description = "Cria um novo usuario com tipo de acesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class), examples = @ExampleObject(value = "{\"id\":1,\"nome\":\"Joao da Silva\",\"email\":\"joao@gestrest.com\",\"login\":\"joao\",\"tipoUsuario\":{\"id\":2,\"nome\":\"Cliente\"}}"))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos"),
            @ApiResponse(responseCode = "409", description = "Email ou login ja cadastrado")
    })
    ResponseEntity<UsuarioResponse> criar(
            @Valid @RequestBody(description = "Dados do usuario", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CriarUsuarioRequest.class), examples = @ExampleObject(value = "{\"nome\":\"Joao da Silva\",\"email\":\"joao@gestrest.com\",\"login\":\"joao\",\"senha\":\"Senha@123\",\"tipoUsuarioId\":2}"))) CriarUsuarioRequest request);
}
