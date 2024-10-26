package R.U.R.U.Repository;

import R.U.R.U.Entity.Residences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidencesRepository extends JpaRepository<Residences, Long> {
}
