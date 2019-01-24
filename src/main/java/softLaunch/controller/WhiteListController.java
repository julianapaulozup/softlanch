package softLaunch.controller;

import org.springframework.http.ResponseEntity;
import softLaunch.domain.WhiteList;
import softLaunch.service.WhiteListService;
import softLaunch.domain.RequestWrapper;
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
    public ResponseEntity<RequestWrapper> postWithMultipleObjects(@RequestBody RequestWrapper requestWrapper){
        return whiteListService.addBatch(requestWrapper);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteWhiteList(){
        whiteListService.deleteWhiteList();
    }

}
