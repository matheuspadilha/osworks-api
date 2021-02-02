package br.com.matheuspadilha.osworks.api.controllers;

import br.com.matheuspadilha.osworks.domain.models.Client;
import br.com.matheuspadilha.osworks.domain.repositories.ClientRepository;
import br.com.matheuspadilha.osworks.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private ClientService clientService;

    @GetMapping("/list")
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> findById(@PathVariable Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        return client.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@Valid @RequestBody Client client) {
        return clientService.save(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> update(@PathVariable Long clientId, @Valid @RequestBody Client client) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        client.setId(clientId);
        Client updatedClient = clientService.save(client);
        return ResponseEntity.ok().body(updatedClient);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
    
        clientService.delete(clientId);

        return ResponseEntity.noContent().build();
    }
}
