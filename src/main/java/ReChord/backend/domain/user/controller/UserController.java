package ReChord.backend.domain.user.controller;

import ReChord.backend.domain.user.service.UserService;
import ReChord.backend.domain.user.service.dto.request.GetSearchUserListRequest;
import ReChord.backend.domain.user.service.dto.request.PatchMyProfileRequest;
import ReChord.backend.domain.user.service.dto.request.PostFriendRequest;
import ReChord.backend.domain.user.service.dto.response.GetFriendListResponse;
import ReChord.backend.domain.user.service.dto.response.GetFriendRequestListResponse;
import ReChord.backend.domain.user.service.dto.response.GetMyProfileResponse;
import ReChord.backend.domain.user.service.dto.response.GetSearchUserListResponse;
import ReChord.backend.global.common.ResponseCode;
import ReChord.backend.global.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private  final UserService userService;

    @GetMapping("/my-profile")
    public ResponseEntity<ResponseDto> getMyProfile() {
        String loginId = "로그인 구현 후 수정";
        GetMyProfileResponse response = userService.getMyProfile(loginId);
        return ResponseEntity.ok(ResponseDto.of(ResponseCode.SUCCESS, response));
    }

    @PatchMapping("/my-profile")
    public ResponseEntity<ResponseDto> patchMyProfile(
            @RequestBody PatchMyProfileRequest request
            ) {
        String loginId = "로그인 구현 후 수정";
        userService.patchMyProfile(loginId, request);
        return ResponseEntity.ok(ResponseDto.of(ResponseCode.SUCCESS));
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDto> deleteUser() {
        String loginId = "로그인 구현 후 수정";
        userService.deleteUser(loginId);
        return ResponseEntity.ok(ResponseDto.of(ResponseCode.SUCCESS));
    }

    @GetMapping("/friend-list")
    public ResponseEntity<ResponseDto> getFriendList(
            @PageableDefault(page = 0, size = 20) Pageable pageable
            ) {
        String loginId = "로그인 구현 후 수정";
        GetFriendListResponse response = userService.getFriendList(loginId, pageable);
        return ResponseEntity.ok(ResponseDto.of(ResponseCode.SUCCESS, response));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDto> getSearch(
            @RequestBody GetSearchUserListRequest request,
            @PageableDefault(page = 0, size = 20) Pageable pageable
    ) {
        GetSearchUserListResponse response = userService.getSearchUser(request, pageable);
        return ResponseEntity.ok(ResponseDto.of(ResponseCode.SUCCESS, response));
    }

    @PostMapping("/friend-request")
    public ResponseEntity<ResponseDto> postFriendRequest(
            @RequestBody PostFriendRequest request
    ) {
        String loginId = "로그인 구현 후 수정";
        userService.postFriendRequest(loginId, request);
        return ResponseEntity.ok(ResponseDto.of(ResponseCode.SUCCESS));
    }

    @PostMapping("/friend-request/{requestId}")
    public ResponseEntity<ResponseDto> acceptFriendRequest(
            @PathVariable("requestId") Long requestId
    ) {
        String loginId = "로그인 구현 후 수정";
        userService.acceptFriendRequest(loginId, requestId);
        return ResponseEntity.ok(ResponseDto.of(ResponseCode.SUCCESS));
    }

    @DeleteMapping("/friend-request/{requestId}")
    public ResponseEntity<ResponseDto> rejectFriendRequest(
            @PathVariable Long requestId
    ) {
        String loginId = "로그인 구현 후 수정";
        userService.rejectFriendRequest(loginId, requestId);
        return ResponseEntity.ok(ResponseDto.of(ResponseCode.SUCCESS));
    }

    @GetMapping("/friend-request/list")
    public ResponseEntity<ResponseDto> getFriendRequestList() {
        String loginId = "로그인 구현 후 수정";
        GetFriendRequestListResponse response = userService.getFriendRequestListResponse(loginId);
        return ResponseEntity.ok(ResponseDto.of(ResponseCode.SUCCESS, response));
    }
}
