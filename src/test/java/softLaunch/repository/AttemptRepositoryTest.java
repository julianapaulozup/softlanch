package softLaunch.repository;

import org.assertj.core.api.Assertions;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import softLaunch.service.attempt.Attempt;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AttemptRepositoryTest {

    @Autowired
    AttemptRepository repository;

    @Test
    public void createShouldPersistDataSucess(){

        Attempt attempt = new Attempt(11L,"Tentativa","111111");
        this.repository.save(attempt);
        Assertions.assertThat(attempt.getId()).isNotNull();
        Assertions.assertThat(attempt.getName()).isEqualTo("Tentativa");
        Assertions.assertThat(attempt.getCpf()).isEqualTo("111111");
    }

    @Test(expected = ComparisonFailure.class)
    public void createShouldPersistDataFail(){

        Attempt attempt = new Attempt(11L,"Tentativa","111111");
        this.repository.save(attempt);
        Assertions.assertThat(attempt.getId()).isNotNull();
        Assertions.assertThat(attempt.getName()).isEqualTo("TentativaErro");
        Assertions.assertThat(attempt.getCpf()).isEqualTo("111111");
    }

    @Test
    public void findAttemptAfterSave() {

        Attempt attempt = new Attempt("Tentativa", "11");
        repository.save(attempt);
        List<Attempt> attempts = repository.findAll();
        assertEquals(7, attempts.size());
        int size = attempts.size() - 1 ;
        Assertions.assertThat(attempts.get(size).getId()).isNotNull();
        Assertions.assertThat(attempts.get(size).getCpf()).isEqualTo("11");
        Assertions.assertThat(attempts.get(size).getName()).isEqualTo("Tentativa");

    }

    @Test
    public void deleteAttemptAfterSave() {

        Attempt attempt = new Attempt("Tentativa", "11");
        repository.save(attempt);
        List <Attempt> foundAttempts = repository.findAll();
        repository.delete(foundAttempts.get(6));
        List <Attempt> attempts = repository.findAll();
        assertEquals(6, attempts.size());

    }

    @Test
    public void updateAttemptAfterSave() {


        Attempt attempt = new Attempt("Tentativa", "11");
        repository.save(attempt);
        attempt.setName("Tentativa Atualizada");
        repository.save(attempt);
        List <Attempt> attempts = repository.findAll();
        int size = attempts.size() - 1;
        assertEquals(7, attempts.size());
        assertEquals("Tentativa Atualizada", attempts.get(size).getName());
    }

    @Test
    public void returnEmptyWheNotFound(){
        Optional<Attempt> found;
        found = repository.findById(111L);
        Assertions.assertThat(!found.isPresent());
    }

}