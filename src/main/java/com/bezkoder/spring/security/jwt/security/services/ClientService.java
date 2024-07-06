// src/main/java/com/bezkoder/spring/security/jwt/services/ClientService.java

package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Client;
import com.bezkoder.spring.security.jwt.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        Client existingClient = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        existingClient.setName(client.getName());
        existingClient.setDni(client.getDni());
        existingClient.setAddress(client.getAddress());  // Corregido
        existingClient.setEmail(client.getEmail());
        existingClient.setPhone(client.getPhone());
        return clientRepository.save(existingClient);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> searchClients(String query) {
        return clientRepository.findByNameContainingOrDniContaining(query, query);
    }

    public Object update(Long id, Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
