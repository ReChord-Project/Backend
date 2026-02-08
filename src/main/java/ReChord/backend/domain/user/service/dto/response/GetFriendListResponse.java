package ReChord.backend.domain.user.service.dto.response;

import ReChord.backend.domain.user.service.dto.item.ProfileInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFriendListResponse {
    private List<ProfileInfo> friendList;
}
