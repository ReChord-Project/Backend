package ReChord.backend.domain.user.controller;

import ReChord.backend.domain.user.service.UserService;
import ReChord.backend.domain.user.service.dto.response.GetMyProfileResponse;
import ReChord.backend.global.common.ResponseCode;
import ReChord.backend.global.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
