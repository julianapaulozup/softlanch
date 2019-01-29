package softLaunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import softLaunch.domain.Client;
import softLaunch.domain.Response;
import softLaunch.exceptionHandler.ClientNotFoundInWhitelistException;
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

    public Response addClient(Client client) {
        repository.save(client);
        return new Response(client.getName(),client.getCpf());
    }

    public Page<Client> getAllClients(Pageable peageble) {
        return repository.findAll(peageble);
    }

    public ResponseEntity<RequestWrapper> addBatch(RequestWrapper requestWrapper) {
        requestWrapper.getWhiteLists().forEach(c->{
                    try{
                        whiteListService.exists(c.getCpf());
                        this.addClient(new Client(c.getName(),c.getCpf()));
                    }catch (ClientNotFoundInWhitelistException e){
                        attemptService.addAttempt(new Attempt(c.getName(),c.getCpf()));
                }});
        return new ResponseEntity<>(requestWrapper, HttpStatus.CREATED);
    }
}