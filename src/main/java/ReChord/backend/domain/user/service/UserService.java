package ReChord.backend.domain.user.service;

import ReChord.backend.domain.user.repository.FriendRepository;
import ReChord.backend.domain.user.repository.FriendRequestRepository;
import ReChord.backend.domain.user.repository.UserRepository;
import ReChord.backend.domain.user.repository.entity.Friend;
import ReChord.backend.domain.user.repository.entity.User;
import ReChord.backend.domain.user.service.dto.request.PatchMyProfileRequest;
import ReChord.backend.domain.user.service.dto.response.GetFriendListResponse;
import ReChord.backend.domain.user.service.dto.response.GetMyProfileResponse;
import ReChord.backend.global.common.ResponseCode;
import ReChord.backend.global.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final FriendRequestRepository friendRequestRepository;

    public User findByLoginIdOrThrow(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new BusinessException(ResponseCode.NOT_EXISTED_USER));
    }


    public GetMyProfileResponse getMyProfile(String loginId) {
        User user = findByLoginIdOrThrow(loginId);
        return GetMyProfileResponse.of(user);
    }

    @Transactional
    public void patchMyProfile(String loginId, PatchMyProfileRequest request) {
        User user = findByLoginIdOrThrow(loginId);

        if(request.getName() != null) {
            user.changeName(request.getName());
        }

        if(request.getProfileImage() != null) {
            user.changeProfileImage(request.getProfileImage());
        }
    }

    @Transactional
    public void deleteUser(String loginId) {
        User user = findByLoginIdOrThrow(loginId);
        userRepository.delete(user);
    }

    public GetFriendListResponse getFriendList(String loginId) {
        User user = findByLoginIdOrThrow(loginId);
        List<Friend> friendList = friendRepository.findAllFriendsProfile(user);
        return GetFriendListResponse.of(friendList);
    }
}
