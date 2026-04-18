package br.com.gestrest.auth.service.adapters.in.web.controller.doc;

import org.springframework.http.ResponseEntity;

import br.com.gestrest.auth.service.adapters.in.web.dto.request.LoginRequest;
import br.com.gestrest.auth.service.adapters.in.web.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Autenticação", description = "API para autenticação de usuários")
public interface AuthControllerDoc {

    @Operation(summary = "Login", description = "Autentica um usuário e retorna token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class), examples = @ExampleObject(value = "{\"type\":\"Bearer\",\"token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\"}"))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    ResponseEntity<LoginResponse> login(
            @Valid @RequestBody(description = "Credenciais de login", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginRequest.class), examples = @ExampleObject(value = "{\"loginOuEmail\":\"joao\",\"senha\":\"Senha@123\"}"))) LoginRequest request);
}