package softLaunch.whitelist.controller;

import org.springframework.http.ResponseEntity;
import softLaunch.attempt.Attempt;
import softLaunch.attempt.AttemptService;
import softLaunch.client.Client;
import softLaunch.client.ClientService;
import softLaunch.whitelist.service.WhiteList;
import softLaunch.whitelist.service.WhiteListService;
import softLaunch.whitelist.service.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("whitelist")
public class WhiteListController {

    @Autowired
    private WhiteListService whiteListService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WhiteList> getAllWhiteLists() {
        return whiteListService.getAllWhiteLists();
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RequestWrapper> postWithMultipleObjects(
            @RequestBody RequestWrapper requestWrapper) {
        requestWrapper.getWhiteLists().stream()
                .forEach(c-> whiteListService.addWhiteList(new WhiteList(c.getName(),c.getCpf())));

        return new ResponseEntity<RequestWrapper>(requestWrapper, HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteWhiteList(){
        whiteListService.deleteWhiteList();
    }

}
