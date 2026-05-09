package br.com.gestrest.pedido.service.domain.exception;

public class RestauranteNaoEncontradoException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradoException(Long id) {
        super(id, "Restaurante");
    }
}