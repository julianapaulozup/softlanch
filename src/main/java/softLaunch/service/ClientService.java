package softLaunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import softLaunch.domain.Client;
import softLaunch.exceptionHandler.ClientNotFoundException;
import softLaunch.repository.ClientRepository;
import softLaunch.domain.Attempt;
import softLaunch.domain.RequestWrapper;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private WhiteListService whiteListService;

    @Autowired
    private AttemptService attemptService;

    public Client addClient(Client client) { return repository.save(client); }

    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public ResponseEntity<RequestWrapper> addBatch(RequestWrapper requestWrapper) {
        requestWrapper.getWhiteLists().stream()
                .forEach(c->{
                    try{
                        whiteListService.exists(c);
                        this.addClient(new Client(c.getName(),c.getCpf()));
                    }catch (ClientNotFoundException e){
                        attemptService.addAttempt(new Attempt(c.getName(),c.getCpf()));
                }});
        return new ResponseEntity<RequestWrapper>(requestWrapper, HttpStatus.CREATED);
    }
}