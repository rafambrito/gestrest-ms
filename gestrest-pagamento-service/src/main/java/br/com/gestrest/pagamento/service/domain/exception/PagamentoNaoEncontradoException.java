package br.com.gestrest.pagamento.service.domain.exception;

public class PagamentoNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PagamentoNaoEncontradoException(Long id) {
        super("Pagamento não encontrado com id: " + id);
    } 

}
