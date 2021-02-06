package br.com.matheuspadilha.osworks.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderServiceInput {
    
    @NotBlank
    private String description;
    
    @NotNull
    private BigDecimal price;
    
    @Valid
    @NotNull
    private ClientIdInput client;
}
