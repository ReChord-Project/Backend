package ReChord.backend.domain.user.service.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PatchMyProfileRequest {
    private String name;
    private String profileImage;
}
