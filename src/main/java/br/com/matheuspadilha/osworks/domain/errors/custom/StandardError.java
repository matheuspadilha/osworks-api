package br.com.matheuspadilha.osworks.domain.errors.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardError implements Serializable {
    
    private static final long serialVersionUID = 3404978768945267283L;
    
    private OffsetDateTime dateTime;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private Map<String, Object> map;
    
    public StandardError(OffsetDateTime dateTime, Integer status, String error, String message, String path){
        this.dateTime = dateTime;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
    
    public StandardError(OffsetDateTime dateTime, Integer status, String error, String path){
        this.dateTime = dateTime;
        this.status = status;
        this.error = error;
        this.path = path;
    }
    
}