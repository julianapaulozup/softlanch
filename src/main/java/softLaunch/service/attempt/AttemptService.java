package softLaunch.service.attempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softLaunch.repository.AttemptRepository;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository repository;

    public Attempt addAttempt(Attempt attempt) {
        return repository.save(attempt);
    }

}
