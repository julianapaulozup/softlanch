package softLanch.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client addClient(Client client) {
        return repository.save(client);

    }

    public List<Client> getAllClients() {
        return repository.findAll();
    }

}