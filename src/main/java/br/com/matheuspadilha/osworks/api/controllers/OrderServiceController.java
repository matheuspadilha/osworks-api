package br.com.matheuspadilha.osworks.api.controllers;

import br.com.matheuspadilha.osworks.domain.models.OrderService;
import br.com.matheuspadilha.osworks.domain.repositories.OrderServiceRepository;
import br.com.matheuspadilha.osworks.domain.services.ManagementOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-service")
public class OrderServiceController {
    
    @Autowired
    private ManagementOrdersService managementOrdersService;
    
    @Autowired
    private OrderServiceRepository orderServiceRepository;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderService create(@Valid @RequestBody OrderService orderService) {
        return managementOrdersService.create(orderService);
    }
    
    @GetMapping
    public List<OrderService> list() {
        return orderServiceRepository.findAll();
    }
    
    @GetMapping("/{orderServiceId}")
    public ResponseEntity<OrderService> search(@PathVariable Long orderServiceId) {
        Optional<OrderService> orderService = orderServiceRepository.findById(orderServiceId);
    
        return orderService.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
