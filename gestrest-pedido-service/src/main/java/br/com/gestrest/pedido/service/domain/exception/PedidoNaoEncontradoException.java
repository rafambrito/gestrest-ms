package br.com.gestrest.pedido.service.domain.exception;

public class PedidoNaoEncontradoException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoException(Long id) {
        super(id, "Pedido");
    }
}
