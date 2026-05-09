package br.com.gestrest.auth.service.domain.exception;

public class TipoUsuarioNaoEncontradoException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public TipoUsuarioNaoEncontradoException(Long id) {
        super(id, "TipoUsuario");
    }
}
