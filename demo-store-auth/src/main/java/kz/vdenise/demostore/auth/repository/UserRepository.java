package kz.vdenise.demostore.auth.repository;

import java.util.Optional;
import kz.vdenise.demostore.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository which provides access to users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByLogin(String login);
}
