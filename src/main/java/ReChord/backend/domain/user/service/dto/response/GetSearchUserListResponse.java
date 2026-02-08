package ReChord.backend.domain.user.service.dto.response;

import ReChord.backend.domain.user.service.dto.item.ProfileInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class GetSearchUserListResponse {
    private List<ProfileInfo> searchUserList;
}
