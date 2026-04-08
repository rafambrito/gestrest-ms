package br.com.gestrest.pagamento.service.adapters.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestrest.pagamento.service.adapters.in.web.controller.doc.PagamentoControllerDoc;
import br.com.gestrest.pagamento.service.adapters.in.web.dto.request.ProcessarPagamentoRequest;
import br.com.gestrest.pagamento.service.adapters.in.web.dto.response.PagamentoResponse;
import br.com.gestrest.pagamento.service.application.usecase.command.pagamento.ProcessarPagamentoCommand;
import br.com.gestrest.pagamento.service.domain.ports.in.pagamento.ProcessarPagamentoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController implements PagamentoControllerDoc {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;

    @Override
    @PostMapping
    public ResponseEntity<PagamentoResponse> processar(@Valid @RequestBody ProcessarPagamentoRequest request) {
        var pagamento = processarPagamentoUseCase
                .processar(new ProcessarPagamentoCommand(request.pedidoId(), request.usuarioId(), request.valor()));
        return ResponseEntity.ok(new PagamentoResponse(
                pagamento.getId(),
                pagamento.getPedidoId(),
                pagamento.getUsuarioId(),
                pagamento.getValor(),
                pagamento.getStatus(),
                pagamento.getDataCriacao()));
    }
}
