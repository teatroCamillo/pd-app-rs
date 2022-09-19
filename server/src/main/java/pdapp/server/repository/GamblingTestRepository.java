package pdapp.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdapp.server.model.GamblingTest;

@Repository
public interface GamblingTestRepository extends JpaRepository<GamblingTest, String> {

    GamblingTest findByUserId(String userId);

}
