package softLanch.client.controller;

import org.springframework.http.ResponseEntity;
import softLanch.attempt.Attempt;
import softLanch.attempt.AttemptService;
import softLanch.client.service.Client;
import softLanch.client.service.WhiteListService;
import softLanch.client.service.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private WhiteListService whiteListService;

    @Autowired
    private AttemptService attemptService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients() {
        return whiteListService.getAllClients();
    }

    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client getClient(@PathVariable ("id") Long id){
                Client client = whiteListService.getClient(id);
                return client;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@RequestBody Client name ,@PathVariable Long id){
        whiteListService.updateClient(id,name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addClient(@RequestBody Client client) {
        whiteListService.addClient(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(@PathVariable Long id){
        whiteListService.deleteClient(id);
    }

    @PostMapping("/batch")
    public ResponseEntity<RequestWrapper> postWithMultipleObjects(
            @RequestBody RequestWrapper requestWrapper) {

        requestWrapper.getClients().stream()
                .forEach(c->{
                    if(whiteListService.exists(c)) {
                        System.out.println("Cliente no Banco");
                    }else
                        attemptService.addAttempt(new Attempt(c.getName(),c.getCpf()));
                });

        return new ResponseEntity<RequestWrapper>(requestWrapper, HttpStatus.OK);
    }
}
