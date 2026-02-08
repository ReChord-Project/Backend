package ReChord.backend.domain.user.service.dto.response;

import ReChord.backend.domain.user.service.dto.item.ProfileInfo;
import lombok.Getter;

@Getter
public class GetMyProfileResponse {
    private ProfileInfo myProfile;
}
