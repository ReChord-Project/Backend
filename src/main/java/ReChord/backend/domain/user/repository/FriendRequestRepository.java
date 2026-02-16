package ReChord.backend.domain.user.repository;

import ReChord.backend.domain.user.repository.entity.FriendRequest;
import ReChord.backend.domain.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    Boolean existsByRequesterAndReceiver(User requester, User receiver);

    @Query("""
            SELECT fr
            FROM FriendRequest fr
            WHERE (fr.requester = :user1 AND fr.receiver = :user2) OR
                  (fr.requester = :user2 AND fr.receiver = :user1)""")
    List<FriendRequest> findRequests(@Param("user1") User user1, @Param("user2") User user2);

    @Query("""
            SELECT fr
            FROM FriendRequest fr JOIN FETCH fr.requester
            WHERE fr.receiver = :user""")
    List<FriendRequest> findByReceiver(User user);
}
