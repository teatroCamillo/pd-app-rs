package pdapp.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdapp.server.model.GamblingQuestion;

@Repository
public interface GamblingQuestionsRepository extends JpaRepository<GamblingQuestion, String> {
}
