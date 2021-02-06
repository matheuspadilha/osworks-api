package br.com.matheuspadilha.osworks.api.model;

import br.com.matheuspadilha.osworks.domain.models.StatusOrderService;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderServiceModel {
    
    private Long id;
    private ClientResumeModel client;
    private String description;
    private BigDecimal price;
    private StatusOrderService status;
    private OffsetDateTime openingDate;
    private OffsetDateTime closingDate;
}
