package softLaunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softLaunch.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

}
