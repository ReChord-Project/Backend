package ReChord.backend.domain.user.service;

import ReChord.backend.domain.user.repository.FriendRepository;
import ReChord.backend.domain.user.repository.FriendRequestRepository;
import ReChord.backend.domain.user.repository.UserRepository;
import ReChord.backend.domain.user.repository.entity.User;
import ReChord.backend.domain.user.service.dto.request.PatchMyProfileRequest;
import ReChord.backend.domain.user.service.dto.response.GetMyProfileResponse;
import ReChord.backend.global.common.ResponseCode;
import ReChord.backend.global.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final FriendRequestRepository friendRequestRepository;

    public GetMyProfileResponse getMyProfile(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new BusinessException(ResponseCode.NOT_EXISTED_USER));

        return GetMyProfileResponse.of(user);
    }

    @Transactional
    public void patchMyProfile(String loginId, PatchMyProfileRequest request) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new BusinessException(ResponseCode.NOT_EXISTED_USER));

        if(request.getName() != null) {
            user.changeName(request.getName());
        }

        if(request.getProfileImage() != null) {
            user.changeProfileImage(request.getProfileImage());
        }
    }
}
