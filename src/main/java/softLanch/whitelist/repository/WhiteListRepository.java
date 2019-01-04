package softLanch.client.repository;

import softLanch.client.service.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    <Client> Optional<Client> findByCpf(String cpf);
}
