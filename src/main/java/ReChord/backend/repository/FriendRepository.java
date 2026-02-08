package ReChord.backend.repository;

import ReChord.backend.repository.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Long, Friend> {
}
