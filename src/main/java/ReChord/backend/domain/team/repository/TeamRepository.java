package ReChord.backend.domain.team.repository;

import ReChord.backend.domain.team.repository.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
