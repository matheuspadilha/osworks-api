package br.com.matheuspadilha.osworks.api.controllers;

import br.com.matheuspadilha.osworks.api.models.OrderServiceInput;
import br.com.matheuspadilha.osworks.api.models.OrderServiceModel;
import br.com.matheuspadilha.osworks.domain.models.OrderService;
import br.com.matheuspadilha.osworks.domain.repositories.OrderServiceRepository;
import br.com.matheuspadilha.osworks.domain.services.ManagementOrdersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order-service")
public class OrderServiceController {
    
    @Autowired
    private ManagementOrdersService managementOrdersService;
    
    @Autowired
    private OrderServiceRepository orderServiceRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderServiceModel create(@Valid @RequestBody OrderServiceInput orderServiceInput) {
        OrderService orderService = toEntity(orderServiceInput);
        
        return toModel(managementOrdersService.create(orderService));
    }
    
    @GetMapping
    public List<OrderServiceModel> list() {
        return toCollectionModel(orderServiceRepository.findAll());
    }
    
    @GetMapping("/{orderServiceId}")
    public ResponseEntity<OrderServiceModel> search(@PathVariable Long orderServiceId) {
        Optional<OrderService> orderService = orderServiceRepository.findById(orderServiceId);
        
        if (orderService.isPresent()) {
            OrderServiceModel orderServiceModel = toModel(orderService.get());
            return ResponseEntity.ok(orderServiceModel);
        }
        
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{orderServiceId}/finished")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finished(@PathVariable Long orderServiceId) {
        managementOrdersService.finished(orderServiceId);
        
    }
    
    private OrderServiceModel toModel(OrderService orderService) {
        return modelMapper.map(orderService, OrderServiceModel.class);
    }
    
    private List<OrderServiceModel> toCollectionModel(List<OrderService> orderServices) {
        return orderServices.stream().map(this::toModel).collect(Collectors.toList());
    }
    
    private OrderService toEntity(OrderServiceInput orderServiceInput) {
        return modelMapper.map(orderServiceInput, OrderService.class);
    }
}
