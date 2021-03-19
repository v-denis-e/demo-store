package kz.vdenise.demostore.auth.repository;

import kz.vdenise.demostore.auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository which provides access to user's roles.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
