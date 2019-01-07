package softLanch.whitelist.service;

import org.springframework.http.HttpStatus;
import softLanch.client.Client;
import softLanch.whitelist.exception.WhiteListNotFoundException;
import softLanch.whitelist.repository.WhiteListRepository;
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

    public WhiteList getWhiteList(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new WhiteListNotFoundException(" whitelist not found with id " + id));

    }

    public WhiteList updateWhiteList(Long id, WhiteList whiteList) {
        return repository.findById(id)
                .map(p -> {
                    p.setName(whiteList.getName());
                    return repository.save(p);
                }).orElseThrow(() -> new WhiteListNotFoundException("whitelist not found with id " + id));
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