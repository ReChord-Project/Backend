package ReChord.backend.domain.user.service.dto.response;

import ReChord.backend.domain.user.repository.entity.User;
import ReChord.backend.domain.user.service.dto.item.ProfileInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetSearchUserListResponse {
    private Page<ProfileInfo> searchUserList;

    public static GetSearchUserListResponse of(Page<User> userList) {
        return new GetSearchUserListResponse(userList.map(ProfileInfo::of));
    }
}
