package ReChord.backend.repository;

import ReChord.backend.repository.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<Long, TeamMember> {
}
