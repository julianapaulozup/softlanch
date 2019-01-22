package softLaunch.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softLaunch.repository.ClientRepository;

import java.util.List;


@Service
@Transactional
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