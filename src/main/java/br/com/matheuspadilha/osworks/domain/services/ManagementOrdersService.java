package br.com.matheuspadilha.osworks.domain.services;

import br.com.matheuspadilha.osworks.domain.models.Comment;
import br.com.matheuspadilha.osworks.domain.errors.exceptions.BusinessException;
import br.com.matheuspadilha.osworks.domain.models.Client;
import br.com.matheuspadilha.osworks.domain.models.OrderService;
import br.com.matheuspadilha.osworks.domain.models.StatusOrderService;
import br.com.matheuspadilha.osworks.domain.repositories.ClientRepository;
import br.com.matheuspadilha.osworks.domain.repositories.CommentRespository;
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
    
    @Autowired
    private CommentRespository commentRespository;
    
    public OrderService create(OrderService orderService) {
        Client client = clientRepository.findById(orderService.getClient().getId())
                .orElseThrow(() -> new BusinessException("Client not found."));
        
        orderService.setClient(client);
        orderService.setStatus(StatusOrderService.OPEN);
        orderService.setOpeningDate(OffsetDateTime.now());
        
        return orderServiceRepository.save(orderService);
    }
    
    public void finished(Long orderServiceId) {
        OrderService orderService = getOrderService(orderServiceId);
        
        orderService.finish();
        
        orderServiceRepository.save(orderService);
    }
    
    public Comment addComment(Long orderServiceId, String description) {
        OrderService orderService = getOrderService(orderServiceId);
        
        Comment comment = new Comment();
        comment.setSendDate(OffsetDateTime.now());
        comment.setDescription(description);
        comment.setOrderService(orderService);
        
        return commentRespository.save(comment);
    }
    
    private OrderService getOrderService(Long orderServiceId) {
        return orderServiceRepository.findById(orderServiceId)
                .orElseThrow(() -> new BusinessException("Order service not found."));
    }
}
