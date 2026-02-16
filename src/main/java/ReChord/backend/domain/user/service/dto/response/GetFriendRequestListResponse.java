package ReChord.backend.domain.user.service.dto.response;

import ReChord.backend.domain.user.repository.entity.FriendRequest;
import ReChord.backend.domain.user.service.dto.item.ProfileInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFriendRequestListResponse {
    private List<ProfileInfo> friendRequestList;

    public static GetFriendRequestListResponse of(List<FriendRequest> friendRequestList) {
        List<ProfileInfo> list = friendRequestList.stream()
                .map(fr -> ProfileInfo.of(fr.getRequester()))
                .toList();
        return new GetFriendRequestListResponse(list);
    }
}
