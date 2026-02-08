package ReChord.backend.domain.user.repository;

import ReChord.backend.domain.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, User> {
}
