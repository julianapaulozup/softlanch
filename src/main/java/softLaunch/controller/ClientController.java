package softLaunch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softLaunch.service.attempt.Attempt;
import softLaunch.service.attempt.AttemptService;
import softLaunch.service.client.Client;
import softLaunch.service.client.ClientService;
import softLaunch.service.whitelist.RequestWrapper;
import softLaunch.service.whitelist.WhiteListService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private WhiteListService whiteListService;

    @Autowired
    private AttemptService attemptService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/batch")
    public ResponseEntity<RequestWrapper> postWithMultipleObjects(
            @RequestBody RequestWrapper requestWrapper) {

        requestWrapper.getWhiteLists().stream()
                .forEach(c->{
                    if(whiteListService.exists(c)) {
                        clientService.addClient(new Client(c.getName(),c.getCpf()));
                    }else
                        attemptService.addAttempt(new Attempt(c.getName(),c.getCpf()));
                });

        return new ResponseEntity<RequestWrapper>(requestWrapper, HttpStatus.CREATED);
    }
}
