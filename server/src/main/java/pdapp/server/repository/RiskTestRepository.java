package pdapp.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdapp.server.model.RiskTest;

import java.util.UUID;

@Repository
public interface RiskTestRepository extends JpaRepository<RiskTest, UUID> {

}
