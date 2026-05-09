package br.com.gestrest.auth.service.domain.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(Long id, String resourceName) {
        super(resourceName + " com id " + id + " não encontrado");
    }
}
