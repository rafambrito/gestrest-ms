package br.com.gestrest.auth.service.domain.exception;

public class EmailJaCadastradoException extends DuplicateResourceException {

    private static final long serialVersionUID = 1L;

    public EmailJaCadastradoException() {
        super("Email já cadastrado");
    }
}
