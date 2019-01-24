package softLaunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import softLaunch.domain.Client;
import softLaunch.exceptionHandler.ClientNotFoundException;
import softLaunch.repository.ClientRepository;
import softLaunch.domain.Attempt;
import softLaunch.domain.RequestWrapper;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private WhiteListService whiteListService;

    @Autowired
    private AttemptService attemptService;

    public Client addClient(Client client) { return repository.save(client); }

    public Page<Client> getAllClients(Pageable peageble) {
        return repository.findAll(peageble);
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