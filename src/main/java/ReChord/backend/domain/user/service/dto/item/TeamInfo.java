package ReChord.backend.domain.user.service.dto.item;

import ReChord.backend.domain.team.repository.entity.TeamMember;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class TeamInfo {
    private Long id;
    private String name;
    private String joinCode;
    private int memberCount;

    public static TeamInfo of(TeamMember teamMember) {
        return TeamInfo.builder()
                .id(teamMember.getTeam().getId())
                .name(teamMember.getTeam().getName())
                .joinCode(teamMember.getTeam().getJoinCode())
                .memberCount(teamMember.getTeam().getMemberCount())
                .build();
    }
}
