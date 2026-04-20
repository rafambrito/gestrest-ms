package br.com.gestrest.restaurante.service.domain.exception;

public class ItemCardapioInvalidoException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ItemCardapioInvalidoException(String message) {
        super(message);
    }
}
