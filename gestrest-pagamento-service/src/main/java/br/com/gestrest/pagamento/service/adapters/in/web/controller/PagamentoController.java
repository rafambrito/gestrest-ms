package br.com.gestrest.pagamento.service.adapters.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestrest.pagamento.service.adapters.in.web.controller.doc.PagamentoControllerDoc;
import br.com.gestrest.pagamento.service.adapters.in.web.dto.request.ProcessarPagamentoRequest;
import br.com.gestrest.pagamento.service.adapters.in.web.dto.response.PagamentoResponse;
import br.com.gestrest.pagamento.service.adapters.in.web.mapper.ConsultarPagamentoWebMapper;
import br.com.gestrest.pagamento.service.application.usecase.command.pagamento.ProcessarPagamentoCommand;
import br.com.gestrest.pagamento.service.domain.model.ports.in.pagamento.BuscarPagamentoPorIdUseCase;
import br.com.gestrest.pagamento.service.domain.model.ports.in.pagamento.ProcessarPagamentoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController implements PagamentoControllerDoc {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;
    private final BuscarPagamentoPorIdUseCase buscarPagamentoPorIdUseCase;
    private final ConsultarPagamentoWebMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<PagamentoResponse> processar(@Valid @RequestBody ProcessarPagamentoRequest request) {
        var pagamento = processarPagamentoUseCase.processar(new ProcessarPagamentoCommand(request.pedidoId(), request.usuarioId(), request.valor()));
        return ResponseEntity.ok(mapper.toResponse(pagamento));
    }   

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> buscarPorId(Long id) {
        var pagamento = buscarPagamentoPorIdUseCase.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponse(pagamento));
    }
}
