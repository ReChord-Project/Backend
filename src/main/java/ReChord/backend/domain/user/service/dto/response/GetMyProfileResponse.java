package ReChord.backend.domain.user.service.dto.response;

import ReChord.backend.domain.user.repository.entity.User;
import ReChord.backend.domain.user.service.dto.item.ProfileInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMyProfileResponse {
    private ProfileInfo myProfile;

    public static GetMyProfileResponse of(User user) {
        return new GetMyProfileResponse(ProfileInfo.of(user));
    }
}
