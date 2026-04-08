package br.com.gestrest.auth.service.domain.exception;

public class LoginJaCadastradoException extends DuplicateResourceException {

    private static final long serialVersionUID = 1L;

    public LoginJaCadastradoException() {
        super("Login já cadastrado");
    }
}
