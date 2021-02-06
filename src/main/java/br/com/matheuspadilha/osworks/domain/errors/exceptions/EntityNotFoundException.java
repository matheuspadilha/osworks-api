package br.com.matheuspadilha.osworks.domain.errors.exceptions;

public class EntityNotFoundException extends BusinessException {
    
    private static final Long serialVersionUID = 1L;
    
    public EntityNotFoundException(String message) {
        super(message);
    }
}
