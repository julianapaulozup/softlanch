package softLaunch.controller;

import org.springframework.http.ResponseEntity;
import softLaunch.service.whitelist.WhiteList;
import softLaunch.service.whitelist.WhiteListService;
import softLaunch.service.whitelist.RequestWrapper;
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
    public ResponseEntity<RequestWrapper> postWithMultipleObjects(
            @RequestBody RequestWrapper requestWrapper) {
        requestWrapper.getWhiteLists().stream()
                .forEach(c-> whiteListService.addWhiteList(new WhiteList(c.getName(),c.getCpf())));

        return new ResponseEntity<RequestWrapper>(requestWrapper, HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteWhiteList(){
        whiteListService.deleteWhiteList();
    }

}
