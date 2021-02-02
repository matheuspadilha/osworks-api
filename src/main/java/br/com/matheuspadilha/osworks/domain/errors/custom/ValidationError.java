package br.com.matheuspadilha.osworks.domain.errors.custom;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    
    private static final long serialVersionUID = -4621951370950451477L;
    
    private final List<FieldMessage> errors = new ArrayList<>();
    
    public ValidationError(Long timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }
    
    public List<FieldMessage> getErrors() {
        return errors;
    }
    
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
    
}
