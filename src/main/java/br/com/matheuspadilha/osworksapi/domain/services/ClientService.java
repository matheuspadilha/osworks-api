package br.com.matheuspadilha.osworksapi.domain.services;

import br.com.matheuspadilha.osworksapi.domain.models.Client;
import br.com.matheuspadilha.osworksapi.domain.repositories.ClientRepository;
import br.com.matheuspadilha.osworksapi.errors.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
