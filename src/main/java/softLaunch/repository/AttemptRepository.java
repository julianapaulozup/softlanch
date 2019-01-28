package softLaunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softLaunch.domain.Attempt;

import java.util.Optional;


@Repository
public interface AttemptRepository  extends JpaRepository<Attempt,Long> {
    <Attempt> Optional<Attempt> findByCpf(String cpf);


}
