package ReChord.backend.domain.user.service.dto.response;

import ReChord.backend.domain.team.repository.entity.TeamMember;
import ReChord.backend.domain.user.service.dto.item.TeamInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetMyTeamListResponse {
    private List<TeamInfo> teamList;

    public static GetMyTeamListResponse of(List<TeamMember> teamMemberList) {
        return new GetMyTeamListResponse(teamMemberList.stream().map(TeamInfo::of).toList());
    }
}
