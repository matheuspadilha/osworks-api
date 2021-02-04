package br.com.matheuspadilha.osworks.domain.services;

import br.com.matheuspadilha.osworks.domain.errors.exceptions.BusinessException;
import br.com.matheuspadilha.osworks.domain.models.Client;
import br.com.matheuspadilha.osworks.domain.models.OrderService;
import br.com.matheuspadilha.osworks.domain.models.StatusOrderService;
import br.com.matheuspadilha.osworks.domain.repositories.ClientRepository;
import br.com.matheuspadilha.osworks.domain.repositories.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ManagementOrdersService {
    
    @Autowired
    private OrderServiceRepository orderServiceRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    public OrderService create(OrderService orderService) {
        Client client = clientRepository.findById(orderService.getClient().getId())
                .orElseThrow(() -> new BusinessException("Client not found."));
        
        orderService.setClient(client);
        orderService.setStatus(StatusOrderService.OPEN);
        orderService.setOpeningDate(OffsetDateTime.now());
        
        return orderServiceRepository.save(orderService);
    }
    
}
