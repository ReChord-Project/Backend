package ReChord.backend.domain.user.service.dto.response;

import ReChord.backend.domain.user.repository.entity.Friend;
import ReChord.backend.domain.user.service.dto.item.ProfileInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFriendListResponse {
    private List<ProfileInfo> friendList;

    public static GetFriendListResponse of(List<Friend> friendList) {
        List<ProfileInfo> list = friendList.stream()
                .map(f -> ProfileInfo.of(f.getFriend()))
                .toList();
        return new GetFriendListResponse(list);
    }
}
