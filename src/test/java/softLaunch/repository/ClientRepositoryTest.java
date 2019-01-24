package softLaunch.repository;


import org.assertj.core.api.Assertions;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import softLaunch.domain.Client;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientRepositoryTest {

    @Autowired
    ClientRepository repository;

    @Test
    public void createShouldPersistDataSucess() {

        Client client = new Client(11L, "Cliente", "111111");
        this.repository.save(client);
        Assertions.assertThat(client.getId()).isNotNull();
        Assertions.assertThat(client.getName()).isEqualTo("Cliente");
        Assertions.assertThat(client.getCpf()).isEqualTo("111111");
    }

    @Test(expected = ComparisonFailure.class)
    public void createShouldPersistDataFail() {

        Client client = new Client(11L, "Cliente", "111111");
        this.repository.save(client);
        Assertions.assertThat(client.getId()).isNotNull();
        Assertions.assertThat(client.getName()).isEqualTo("ClienteErro");
        Assertions.assertThat(client.getCpf()).isEqualTo("111111");
    }

    @Test
    public void findClientAfterSave() {

        Client client = new Client("Cliente", "11111");
        repository.save(client);
        List<Client> clients = repository.findAll();
        assertEquals(6, clients.size());
        int size = clients.size() - 1;
        Assertions.assertThat(clients.get(size).getId()).isNotNull();
        Assertions.assertThat(clients.get(size).getCpf()).isEqualTo("11111");
        Assertions.assertThat(clients.get(size).getName()).isEqualTo("Cliente");

    }

    @Test
    public void deleteClientAfterSave() {

        Client client = new Client("Cliente", "1111");
        repository.save(client);
        List<Client> foundClients = repository.findAll();
        repository.delete(foundClients.get(5));
        List<Client> clients = repository.findAll();
        assertEquals(5, clients.size());

    }

    @Test
    public void updateClientAfterSave() {


        Client client = new Client("Cliente", "1111");
        repository.save(client);
        client.setName("Cliente Atualizada");
        repository.save(client);
        List<Client> clients = repository.findAll();
        int size = clients.size() - 1;
        assertEquals(6, clients.size());
        assertEquals("Cliente Atualizada", clients.get(size).getName());
    }

    @Test
    public void returnEmptyWheNotFound() {
        Optional<Client> found;
        found = repository.findById(111L);
        Assertions.assertThat(!found.isPresent());
    }

}