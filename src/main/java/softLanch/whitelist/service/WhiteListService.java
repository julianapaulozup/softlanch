package softLanch.client.service;

import softLanch.client.exception.ClientNotFoundException;
import softLanch.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WhiteListService {

    @Autowired
    private ClientRepository repository;

    public Client addClient(Client product) {
            return repository.save(product);

    }

    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public Client getClient(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new ClientNotFoundException(" softLanch.client.softLanch.client not found with id " + id));

    }

    public Client updateClient(Long id, Client product) {
        return repository.findById(id)
                .map(p -> {
                    p.setName(product.getName());
                    return repository.save(p);
                }).orElseThrow(() -> new ClientNotFoundException("softLanch.client.softLanch.client not found with id " + id));
    }

    public ResponseEntity<?> deleteClient(Long id) {
        return repository.findById(id)
                .map(p -> {
                    repository.delete(p);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ClientNotFoundException("softLanch.client.softLanch.client not found with id " + id));

    }

    public boolean exists(Client c) {
         if(repository.findByCpf(c.getCpf()).isPresent())
             return true;
         else
             return false;

    }
}