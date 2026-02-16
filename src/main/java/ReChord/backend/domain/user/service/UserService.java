package ReChord.backend.domain.user.service;

import ReChord.backend.domain.team.repository.TeamMemberRepository;
import ReChord.backend.domain.team.repository.TeamRepository;
import ReChord.backend.domain.user.repository.FriendRepository;
import ReChord.backend.domain.user.repository.FriendRequestRepository;
import ReChord.backend.domain.user.repository.UserRepository;
import ReChord.backend.domain.user.repository.entity.Friend;
import ReChord.backend.domain.user.repository.entity.FriendRequest;
import ReChord.backend.domain.user.repository.entity.User;
import ReChord.backend.domain.user.service.dto.request.GetSearchUserListRequest;
import ReChord.backend.domain.user.service.dto.request.PatchMyProfileRequest;
import ReChord.backend.domain.user.service.dto.request.PostFriendRequest;
import ReChord.backend.domain.user.service.dto.response.*;
import ReChord.backend.global.common.ResponseCode;
import ReChord.backend.global.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final TeamMemberRepository teamMemberRepository;

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

        if (request.getName() != null) {
            user.changeName(request.getName());
        }

        if (request.getProfileImage() != null) {
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

    @Transactional
    public void postFriendRequest(String loginId, PostFriendRequest request) {
        User requester = findByLoginIdOrThrow(loginId);
        User receiver = findByLoginIdOrThrow(request.getReceiverLoginId());
        if (requester.getLoginId().equals(receiver.getLoginId())) {
            throw new BusinessException(ResponseCode.CANNOT_ADD_SELF);
        }
        if (friendRepository.existsByUserAndFriend(requester, receiver) ||
                friendRepository.existsByUserAndFriend(receiver, requester)) {
            throw new BusinessException(ResponseCode.ALREADY_FRIEND);
        }
        if (friendRequestRepository.existsByRequesterAndReceiver(requester, receiver)) {
            throw new BusinessException(ResponseCode.ALREADY_REQUESTED);
        }
        friendRequestRepository.save(FriendRequest.of(requester, receiver));
    }

    @Transactional
    public void acceptFriendRequest(String loginId, Long requestId) {
        User user = findByLoginIdOrThrow(loginId);

        FriendRequest request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new BusinessException(ResponseCode.NOT_EXISTED_REQUEST));

        User requester = request.getRequester();
        User receiver = request.getReceiver();

        if (!user.equals(receiver)) {
            throw new BusinessException(ResponseCode.NO_PERMISSION);
        }

        List<FriendRequest> relatedRequests = friendRequestRepository
                .findRequests(requester, receiver);
        friendRequestRepository.deleteAll(relatedRequests);

        if (friendRepository.existsByUserAndFriend(requester, receiver) ||
                friendRepository.existsByUserAndFriend(receiver, requester)) {
            throw new BusinessException(ResponseCode.ALREADY_FRIEND);
        }

        friendRepository.saveAll(List.of(Friend.of(requester, receiver), Friend.of(receiver, requester)));
    }

    @Transactional
    public void rejectFriendRequest(String loginId, Long requestId) {
        User receiver = findByLoginIdOrThrow(loginId);

        FriendRequest request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new BusinessException(ResponseCode.NOT_EXISTED_REQUEST));

        if (!request.getReceiver().equals(receiver)) {
            throw new BusinessException(ResponseCode.NO_PERMISSION);
        }

        friendRequestRepository.delete(request);
    }

    public GetFriendRequestListResponse getFriendRequestListResponse(String loginId) {
        User user = findByLoginIdOrThrow(loginId);
        return GetFriendRequestListResponse.of(friendRequestRepository.findByReceiver(user));
    }

    public GetMyTeamListResponse getMyTeamList(String loginId) {
        User user = findByLoginIdOrThrow(loginId);
        return GetMyTeamListResponse.of(teamMemberRepository.findByUser(user));
    }
}
