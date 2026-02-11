package ReChord.backend.domain.user.repository;

import ReChord.backend.domain.user.repository.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
}
