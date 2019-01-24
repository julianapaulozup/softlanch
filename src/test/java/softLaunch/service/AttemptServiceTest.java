package softLaunch.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import softLaunch.repository.AttemptRepository;
import softLaunch.domain.Attempt;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AttemptServiceTest {

    @InjectMocks
    private AttemptService attemptService;

    @Mock
    private AttemptRepository repository;

    @Test
    public void whenValidAttempt_thenAttemptShouldAddSucess(){
        Attempt found = new Attempt(11111L, "Tentativa", "8");
        when(repository.save(any())).thenReturn(found);


        found = attemptService.addAttempt(found);

        Assertions.assertThat(found.getId()).isNotNull();
        Assertions.assertThat(found.getName()).isEqualTo("Tentativa");

    }

    @Test(expected = AssertionError.class)
    public void whenInvalidAttempt_thenAttemptShouldAddFail(){
        Attempt found = new Attempt();
        when(repository.save(any())).thenReturn(found);
        found = attemptService.addAttempt(found);

        Assertions.assertThat(found.getId()).isNotNull();
        Assertions.assertThat(found.getName()).isEqualTo("Tentativa");
    }

}
