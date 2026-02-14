package ReChord.backend.domain.user.repository;

import ReChord.backend.domain.user.repository.entity.FriendRequest;
import ReChord.backend.domain.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    Boolean existsByRequesterAndReceiver(User requester, User receiver);

    List<FriendRequest> findByRequesterAndReceiverOrReverse(User requester, User receiver);
}
