package softLaunch.service.whitelist;

import org.springframework.http.HttpStatus;
import softLaunch.repository.WhiteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WhiteListService {

    @Autowired
    private WhiteListRepository repository;

    public List<WhiteList> getAllWhiteLists() {
        return repository.findAll();
    }


    public ResponseEntity<?> deleteWhiteList() {
        repository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public boolean exists(WhiteList c) {

        return repository.findByCpf(c.getCpf()).isPresent();
    }

    public WhiteList addWhiteList(WhiteList whiteList) {
        return repository.save(whiteList);
    }
}