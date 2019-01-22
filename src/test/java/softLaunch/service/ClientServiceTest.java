package softLaunch.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import softLaunch.repository.ClientRepository;
import softLaunch.service.client.Client;
import softLaunch.service.client.ClientService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository repository;

    @Test
    public void whenValidClient_thenClientShouldAddSucess(){

        Client found = new Client(111L, "Cliente", "88");
        when(repository.save(any())).thenReturn(found);
        found = clientService.addClient(found);


        Assertions.assertThat(found.getId()).isNotNull();
        Assertions.assertThat(found.getName()).isEqualTo("Cliente");
    }

    @Test(expected = AssertionError.class)
    public void whenInvalidClient_thenClientShouldAddFail(){

        Client found = new Client();
        when(repository.save(any())).thenReturn(found);
        found = clientService.addClient(found);

        Assertions.assertThat(found.getId()).isNotNull();
        Assertions.assertThat(found.getName()).isEqualTo("Cliente");
    }

    @Test
    public void whenSearch_thenGetReturnListSucess(){

        List<Client> found = clientService.getAllClients();
        Assertions.assertThat(found.size()).isNotNull();

    }

}