package ReChord.backend.domain.user.service;

import ReChord.backend.domain.user.repository.FriendRepository;
import ReChord.backend.domain.user.repository.FriendRequestRepository;
import ReChord.backend.domain.user.repository.UserRepository;
import ReChord.backend.domain.user.repository.entity.Friend;
import ReChord.backend.domain.user.repository.entity.User;
import ReChord.backend.domain.user.service.dto.request.GetSearchUserListRequest;
import ReChord.backend.domain.user.service.dto.request.PatchMyProfileRequest;
import ReChord.backend.domain.user.service.dto.response.GetFriendListResponse;
import ReChord.backend.domain.user.service.dto.response.GetMyProfileResponse;
import ReChord.backend.domain.user.service.dto.response.GetSearchUserListResponse;
import ReChord.backend.global.common.ResponseCode;
import ReChord.backend.global.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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

    public GetFriendListResponse getFriendList(String loginId, Pageable pageable) {
        User user = findByLoginIdOrThrow(loginId);
        Page<Friend> friendList = friendRepository.findAllFriendsProfile(user, pageable);
        return GetFriendListResponse.of(friendList);
    }

    public GetSearchUserListResponse getSearchUser(GetSearchUserListRequest request, Pageable pageable) {
        Page<User> userList = userRepository.searchUsers(request.getSearchWord(), pageable);
        return GetSearchUserListResponse.of(userList);
    }
}
