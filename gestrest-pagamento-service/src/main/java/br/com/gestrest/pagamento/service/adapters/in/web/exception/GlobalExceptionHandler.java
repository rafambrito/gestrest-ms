package br.com.gestrest.pagamento.service.adapters.in.web.exception;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult br = ex.getBindingResult();
        var fieldErrors = br.getFieldErrors().stream().map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.toList());
        var problem = buildProblem(HttpStatus.BAD_REQUEST, "Validation failed", request);
        problem.setProperty("errors", fieldErrors);
        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler({ IllegalArgumentException.class, RuntimeException.class })
    public ResponseEntity<ProblemDetail> handleRuntime(RuntimeException ex, WebRequest request) {
        var status = ex instanceof IllegalArgumentException ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
        var detail = ex instanceof IllegalArgumentException ? ex.getMessage() : "Ocorreu um erro inesperado";
        return ResponseEntity.status(status).body(buildProblem(status, detail, request));
    }

    private ProblemDetail buildProblem(HttpStatus status, String detail, WebRequest request) {
        var problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setTitle(status.getReasonPhrase());
        problem.setProperty("timestamp", OffsetDateTime.now());
        problem.setProperty("path", request.getDescription(false).replace("uri=", ""));
        return problem;
    }
}
