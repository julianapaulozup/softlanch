package softLaunch.repository;

import softLaunch.service.whitelist.WhiteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WhiteListRepository extends JpaRepository<WhiteList,Long> {

    <WhiteList> Optional<WhiteList> findByCpf(String cpf);
}
