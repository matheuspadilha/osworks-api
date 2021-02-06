package br.com.matheuspadilha.osworks.domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class OrderService {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Client client;
    
    private String description;
    private BigDecimal price;
    
    @Enumerated(EnumType.STRING)
    private StatusOrderService status;
    
    private OffsetDateTime openingDate;
    private OffsetDateTime closingDate;
    
    @OneToMany(mappedBy = "orderService")
    private List<Comment> comments = new ArrayList<>();
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderService that = (OrderService) o;
        return id.equals(that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
