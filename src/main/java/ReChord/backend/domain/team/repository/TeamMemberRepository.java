package ReChord.backend.domain.team.repository;

import ReChord.backend.domain.team.repository.entity.TeamMember;
import ReChord.backend.domain.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    @Query("""
            SELECT tm
            FROM TeamMember tm JOIN FETCH tm.team
            WHERE tm.user = :user""")
    List<TeamMember> findByUser(User user);
}
