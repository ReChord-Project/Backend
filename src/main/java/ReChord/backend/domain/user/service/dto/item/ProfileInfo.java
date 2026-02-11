package ReChord.backend.domain.user.service.dto.item;

import ReChord.backend.domain.user.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ProfileInfo {
    private String name;
    private String loginId;
    private String profileImage;

    public static ProfileInfo of(User user) {
        return ProfileInfo.builder()
                .name(user.getName())
                .loginId(user.getLoginId())
                .profileImage(user.getProfileImage())
                .build();
    }
}
