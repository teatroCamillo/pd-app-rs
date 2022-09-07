package pdapp.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdapp.server.model.RiskTest;

import java.util.List;
import java.util.UUID;

@Repository
public interface RiskTestRepository extends JpaRepository<RiskTest, String> {

    @Query(value = "SELECT id FROM risk_tests WHERE user_id = :userId", nativeQuery = true)
    List<RiskTest> hasUserCompletedRiskTest(String userId);
}
