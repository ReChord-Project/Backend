package ReChord.backend.domain.user.service.dto.response;

import ReChord.backend.domain.user.repository.entity.Friend;
import ReChord.backend.domain.user.service.dto.item.ProfileInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFriendListResponse {
    private Page<ProfileInfo> friendList;

    public static GetFriendListResponse of(Page<Friend> friendList) {
        Page<ProfileInfo> list = friendList.map(f -> ProfileInfo.of(f.getFriend()));
        return new GetFriendListResponse(list);
    }
}
