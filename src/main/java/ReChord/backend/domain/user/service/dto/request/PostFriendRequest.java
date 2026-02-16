package ReChord.backend.domain.user.service.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostFriendRequest {
    private String receiverLoginId;
}
