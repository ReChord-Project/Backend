package ReChord.backend.domain.user.repository;

import ReChord.backend.domain.user.repository.entity.Friend;
import ReChord.backend.domain.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("SELECT f " +
            "FROM Friend f JOIN FETCH f.friend " +
            "WHERE f.user = :user")
    List<Friend> findAllFriendsProfile(@Param("user") User user);
}
