package br.com.gestrest.auth.service.adapters.in.web.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestrest.auth.service.adapters.in.web.controller.doc.UsuarioControllerDoc;
import br.com.gestrest.auth.service.adapters.in.web.dto.request.CriarUsuarioRequest;
import br.com.gestrest.auth.service.adapters.in.web.dto.response.UsuarioResponse;
import br.com.gestrest.auth.service.adapters.in.web.mapper.UsuarioWebMapper;
import br.com.gestrest.auth.service.domain.model.ports.in.usuario.CriarUsuarioUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController implements UsuarioControllerDoc {

    private final CriarUsuarioUseCase criarUseCase;
    private final UsuarioWebMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody CriarUsuarioRequest request) {
        var usuario = criarUseCase.criar(mapper.toDomain(request));
        var response = mapper.toResponse(usuario);
        return ResponseEntity.created(URI.create("/api/v1/usuarios/" + response.id())).body(response);
    }
}
