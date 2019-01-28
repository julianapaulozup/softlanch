package softLaunch.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import softLaunch.domain.Response;
import softLaunch.repository.ClientRepository;
import softLaunch.domain.Client;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository repository;

    @Test
    public void whenValidClient_thenClientShouldAddSucess(){

        Client test = new Client(111L, "Cliente", "88");
        when(repository.save(any())).thenReturn(test);
        Response found = clientService.addClient(test);

        Assertions.assertThat(found.getCpf()).isEqualTo("88");
        Assertions.assertThat(found.getName()).isEqualTo("Cliente");
    }

    @Test(expected = AssertionError.class)
    public void whenInvalidClient_thenClientShouldAddFail(){

        Client test = new Client();
        when(repository.save(any())).thenReturn(test);
        Response found = clientService.addClient(test);

        Assertions.assertThat(found.getCpf()).isEqualTo("88");
        Assertions.assertThat(found.getName()).isEqualTo("Cliente");
    }

    @Test
    public void whenSearch_thenGetReturnListSucess(){

        PageRequest pageRequest = PageRequest.of(0,10);
        Client client = new Client(11L,"Nome","111111");

        List<Client> allClients = Arrays.asList(client);
        final Page<Client> page = new PageImpl<>(allClients);
        given(clientService.getAllClients(any(PageRequest.class))).willReturn(page);

        Page<Client> found = clientService.getAllClients(pageRequest);
        Assertions.assertThat(found.getTotalElements()).isEqualTo(1);

    }

}