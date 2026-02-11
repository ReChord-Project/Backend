package ReChord.backend.domain.user.repository;

import ReChord.backend.domain.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Long, User> {
    Optional<User> findByLoginId(String loginId);
}
