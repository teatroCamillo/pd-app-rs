package pdapp.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdapp.server.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    Optional<User> findById(UUID userId);
}
