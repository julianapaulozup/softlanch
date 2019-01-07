package softLaunch.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softLaunch.attempt.Attempt;
import softLaunch.attempt.AttemptService;
import softLaunch.whitelist.service.RequestWrapper;
import softLaunch.whitelist.service.WhiteList;
import softLaunch.whitelist.service.WhiteListService;

import java.util.List;

@RestController
@RequestMapping("client")
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RequestWrapper> postWithMultipleObjects(
            @RequestBody RequestWrapper requestWrapper) {

        requestWrapper.getWhiteLists().stream()
                .forEach(c->{
                    if(whiteListService.exists(c)) {
                        clientService.addClient(new Client(c.getName(),c.getCpf()));
                    }else
                        attemptService.addAttempt(new Attempt(c.getName(),c.getCpf()));
                });

        return new ResponseEntity<RequestWrapper>(requestWrapper, HttpStatus.OK);
    }
}
