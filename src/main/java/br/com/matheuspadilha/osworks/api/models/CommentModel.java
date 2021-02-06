package br.com.matheuspadilha.osworks.api.models;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CommentModel {
    
    private Long id;
    private String description;
    private OffsetDateTime sendDate;
}
