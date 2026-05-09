package br.com.gestrest.pedido.service.domain.exception;

public class RecursoEmUsoException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public RecursoEmUsoException(String message) {
        super(message);
    }
}
