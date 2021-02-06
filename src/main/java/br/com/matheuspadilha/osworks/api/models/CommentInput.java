package br.com.matheuspadilha.osworks.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentInput {
    
    @NotBlank
    private String description;
}
