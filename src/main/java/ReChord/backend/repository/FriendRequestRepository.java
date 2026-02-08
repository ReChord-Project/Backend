package ReChord.backend.repository;

import ReChord.backend.repository.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository extends JpaRepository<Long, FriendRequest> {
}
