package com.app.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.persistence.crud.ClientRepository;
import com.app.persistence.entities.Client;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
    
    public void save(Client client) {
        String encryptedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encryptedPassword);
        
        this.clientRepository.save(client);
    }

    public ResponseEntity<Client> updateClient(Long id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();
            existingClient.setName(client.getName());
            existingClient.setSurname(client.getSurname());
            existingClient.setEmail(client.getEmail());
            existingClient.setPhoneNumber(client.getPhoneNumber());

            Client updatedClient = clientRepository.save(existingClient);
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}