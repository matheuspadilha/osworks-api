package br.com.matheuspadilha.osworks.domain.errors.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldMessage implements Serializable {
    
    private static final long serialVersionUID = 4518955296495020131L;
    
    private String fieldName;
    private String message;
    
}
