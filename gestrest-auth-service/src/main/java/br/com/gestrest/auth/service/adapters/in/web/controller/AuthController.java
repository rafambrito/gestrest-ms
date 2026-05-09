package br.com.gestrest.auth.service.adapters.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestrest.auth.service.adapters.in.web.controller.doc.AuthControllerDoc;
import br.com.gestrest.auth.service.adapters.in.web.dto.request.LoginRequest;
import br.com.gestrest.auth.service.adapters.in.web.dto.response.LoginResponse;
import br.com.gestrest.auth.service.adapters.in.web.mapper.AuthWebMapper;
import br.com.gestrest.auth.service.domain.model.ports.in.usuario.AutenticarUsuarioUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDoc {

    private final AutenticarUsuarioUseCase autenticarUseCase;
    private final AuthWebMapper mapper;

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        var token = autenticarUseCase.autenticar(mapper.toDomain(request));
        var response = new LoginResponse("Bearer", token);
        return ResponseEntity.ok(response);
    }
}