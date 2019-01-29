package softLaunch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import softLaunch.domain.RequestWrapper;
import softLaunch.domain.Response;
import softLaunch.domain.WhiteList;
import softLaunch.exceptionHandler.ClientNotFoundInWhitelistException;
import softLaunch.repository.WhiteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WhiteListService {

    @Autowired
    private WhiteListRepository repository;

    public Page<WhiteList> getAllWhiteLists(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ResponseEntity<?> deleteWhiteList() {
        repository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean exists(String c)  {
         if(!repository.findByCpf(c).isPresent())
             throw new ClientNotFoundInWhitelistException("Cliente não cadastrado na base");
         return true;
    }

    public Response addWhiteList(WhiteList  whiteList) {
        repository.save( whiteList);
        return new Response( whiteList.getName(), whiteList.getCpf());

    }
    public ResponseEntity<RequestWrapper> addBatch(RequestWrapper requestWrapper) {
        requestWrapper.getWhiteLists().forEach(c-> this.addWhiteList(new WhiteList(c.getName(),c.getCpf())));

        return new ResponseEntity<>(requestWrapper, HttpStatus.CREATED);
    }
}