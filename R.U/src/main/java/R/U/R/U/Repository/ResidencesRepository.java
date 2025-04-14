package R.U.R.U.Repository;

import R.U.R.U.Entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidencesRepository extends JpaRepository<Residence, Long> {
}
