package br.com.matheuspadilha.osworks.api.controllers;

import br.com.matheuspadilha.osworks.domain.errors.exceptions.EntityNotFoundException;
import br.com.matheuspadilha.osworks.domain.models.Comment;
import br.com.matheuspadilha.osworks.api.models.CommentInput;
import br.com.matheuspadilha.osworks.api.models.CommentModel;
import br.com.matheuspadilha.osworks.domain.models.OrderService;
import br.com.matheuspadilha.osworks.domain.repositories.OrderServiceRepository;
import br.com.matheuspadilha.osworks.domain.services.ManagementOrdersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order-service/{orderServiceId}/comments")
public class CommentController {
    
    @Autowired
    private ManagementOrdersService managementOrdersService;
    
    @Autowired
    private OrderServiceRepository orderServiceRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping
    public List<CommentModel> list(@PathVariable Long orderServiceId) {
        OrderService orderService = orderServiceRepository.findById(orderServiceId)
                .orElseThrow( () -> new EntityNotFoundException("Order service not found."));
        
        return toCollectionModel(orderService.getComments());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentModel addComment(@PathVariable Long orderServiceId, @Valid @RequestBody CommentInput commentInput) {
        Comment comment = managementOrdersService.addComment(orderServiceId, commentInput.getDescription());
        
        return toModel(comment);
    }
    
    private CommentModel toModel(Comment comment) {
        return modelMapper.map(comment, CommentModel.class);
    }
    
    private List<CommentModel> toCollectionModel(List<Comment> comments) {
        return comments.stream().map(this::toModel).collect(Collectors.toList());
    }
}
