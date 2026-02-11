package ReChord.backend.domain.user.repository;

import ReChord.backend.domain.user.repository.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
