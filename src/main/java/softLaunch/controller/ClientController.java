package softLaunch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softLaunch.domain.Client;
import softLaunch.service.ClientService;
import softLaunch.domain.RequestWrapper;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Client> getAllClients(Pageable pegeable) {
        return clientService.getAllClients(pegeable);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RequestWrapper> postWithMultipleObjects(@RequestBody RequestWrapper requestWrapper){
        return clientService.addBatch(requestWrapper);
    }
}
