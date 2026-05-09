package br.com.gestrest.pedido.service.domain.exception;

public class UnauthorizedOperationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UnauthorizedOperationException(String message) {
        super(message);
    }
}
