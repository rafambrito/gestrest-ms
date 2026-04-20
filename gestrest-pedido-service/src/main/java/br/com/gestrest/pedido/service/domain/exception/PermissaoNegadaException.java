package br.com.gestrest.pedido.service.domain.exception;

public class PermissaoNegadaException extends UnauthorizedOperationException {
    private static final long serialVersionUID = 1L;

    public PermissaoNegadaException(String message) {
        super(message);
    }
}