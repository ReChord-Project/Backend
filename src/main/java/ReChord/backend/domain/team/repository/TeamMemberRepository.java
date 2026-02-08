package ReChord.backend.domain.team.repository;

import ReChord.backend.domain.team.repository.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<Long, TeamMember> {
}
