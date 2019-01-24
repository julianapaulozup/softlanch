package softLaunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softLaunch.domain.Attempt;


@Repository
public interface AttemptRepository  extends JpaRepository<Attempt,Long> {

}
