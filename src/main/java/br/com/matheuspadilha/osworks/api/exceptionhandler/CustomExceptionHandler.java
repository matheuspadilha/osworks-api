package br.com.matheuspadilha.osworks.api.exceptionhandler;

import br.com.matheuspadilha.osworks.domain.errors.custom.ValidationError;
import br.com.matheuspadilha.osworks.domain.errors.exceptions.BusinessException;
import br.com.matheuspadilha.osworks.domain.errors.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationError error = new ValidationError(OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "Error validation", ((ServletWebRequest) request).getRequest().getRequestURI());
    
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        return super.handleExceptionInternal(ex, error, headers, status, request);
    }
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        
        ValidationError error = new ValidationError(OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "Error verification", ((ServletWebRequest) request).getRequest().getRequestURI());
        
        error.addError(null, ex.getMessage());
    
        return super.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleBusiness(EntityNotFoundException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        
        ValidationError error = new ValidationError(OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "Error verification", ((ServletWebRequest) request).getRequest().getRequestURI());
        
        error.addError(null, ex.getMessage());
        
        return super.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}
