package br.com.matheuspadilha.osworksapi.errors.exceptions;

public class BusinessException extends RuntimeException {
    
    private static final long serialVersionUID = -1388262764615848008L;
    
    public BusinessException(String message) {
        super(message);
    }
}
