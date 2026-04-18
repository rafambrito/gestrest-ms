package br.com.gestrest.auth.service.domain.exception;

public class AutenticacaoException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public AutenticacaoException(String message) {
        super(message);
    }
}