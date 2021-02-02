package br.com.matheuspadilha.osworks.domain.services;

import br.com.matheuspadilha.osworks.domain.models.Client;
import br.com.matheuspadilha.osworks.domain.repositories.ClientRepository;
import br.com.matheuspadilha.osworks.domain.errors.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public Client save(Client client) {
        Client clientExists = clientRepository.findByEmail(client.getEmail());
        
        if(clientExists != null && !clientExists.equals(client)){
            throw new BusinessException("The e-mail is already being used!");
        }
        
        return clientRepository.save(client);
    }
    
    public void delete(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
