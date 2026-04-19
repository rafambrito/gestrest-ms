package br.com.gestrest.api.adapters.in.web.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import br.com.gestrest.pedido.service.adapter.in.web.exception.GlobalExceptionHandler;
import br.com.gestrest.pedido.service.domain.exception.BusinessException;
import br.com.gestrest.pedido.service.domain.exception.DuplicateResourceException;
import br.com.gestrest.pedido.service.domain.exception.EntityNotFoundException;
import br.com.gestrest.pedido.service.domain.exception.UnauthorizedOperationException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    static class ValidationTarget {
        @SuppressWarnings("unused")
        void handle(String body) {
        }
    }

    @Test
    void handleNotFound() {
        WebRequest req = new ServletWebRequest(new MockHttpServletRequest());
        ResponseEntity<ProblemDetail> r = handler.handleNotFound(new EntityNotFoundException("Restaurante id 99 nao encontrado"), req);
        ProblemDetail body = Objects.requireNonNull(r.getBody());
        assertEquals(404, body.getStatus());
    }

    @Test
    void handleConflict() {
        WebRequest req = new ServletWebRequest(new MockHttpServletRequest());
        ResponseEntity<ProblemDetail> r = handler.handleConflict(new DuplicateResourceException("Email ja cadastrado: joao.silva@gestrest.com"), req);
        ProblemDetail body = Objects.requireNonNull(r.getBody());
        assertEquals(409, body.getStatus());
    }

    @Test
    void handleBusiness() {
        WebRequest req = new ServletWebRequest(new MockHttpServletRequest());
        ResponseEntity<ProblemDetail> r = handler.handleBusiness(new BusinessException("Apenas usuarios com perfil DONO_RESTAURANTE podem criar restaurantes"), req);
        ProblemDetail body = Objects.requireNonNull(r.getBody());
        assertEquals(400, body.getStatus());
    }

    @Test
    void handleUnauthorized() {
        WebRequest req = new ServletWebRequest(new MockHttpServletRequest());
        ResponseEntity<ProblemDetail> r = handler.handleUnauthorized(new UnauthorizedOperationException("Operacao nao permitida para o usuario autenticado"), req);
        ProblemDetail body = Objects.requireNonNull(r.getBody());
        assertEquals(403, body.getStatus());
    }

    @Test
    void handleIllegalArgument() {
        WebRequest req = new ServletWebRequest(new MockHttpServletRequest());
        ResponseEntity<ProblemDetail> r = handler.handleIllegalArgument(new IllegalArgumentException("Dono é obrigatório"), req);
        ProblemDetail body = Objects.requireNonNull(r.getBody());
        assertEquals(400, body.getStatus());
        assertEquals("Dono é obrigatório", body.getDetail());
    }

    @Test
    void handleRuntime() {
        WebRequest req = new ServletWebRequest(new MockHttpServletRequest());
        ResponseEntity<ProblemDetail> r = handler.handleRuntime(new RuntimeException("Erro inesperado ao processar atualizacao de usuario"), req);
        ProblemDetail body = Objects.requireNonNull(r.getBody());
        assertEquals(500, body.getStatus());
    }

    @Test
    void handleValidation() throws Exception {
        MockHttpServletRequest servlet = new MockHttpServletRequest();
        WebRequest req = new ServletWebRequest(servlet);

        var target = new Object();
        var binding = new BeanPropertyBindingResult(target, "obj");
        binding.addError(new FieldError("obj", "email", "Email deve estar em um formato valido"));

        Method method = Objects.requireNonNull(ValidationTarget.class.getDeclaredMethod("handle", String.class));
        MethodParameter parameter = new MethodParameter(method, 0);
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(parameter, binding);
        ResponseEntity<ProblemDetail> r = handler.handleValidation(ex, req);
        ProblemDetail body = Objects.requireNonNull(r.getBody());
        assertEquals(400, body.getStatus());
        @SuppressWarnings("unchecked")
        List<String> errors = (List<String>) body.getProperties().get("errors");
        assertEquals(1, Objects.requireNonNull(errors).size());
    }
}
