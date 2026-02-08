package ReChord.backend.repository;

import ReChord.backend.repository.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Long, Team> {
}
