package ReChord.backend.domain.user.service.dto.response;

import ReChord.backend.domain.user.service.dto.item.TeamInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class GetTeamList {
    private List<TeamInfo> teamList;
}
