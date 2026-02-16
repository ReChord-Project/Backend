package ReChord.backend.global.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseDto {
    private String message;
    private Object data;

    public static ResponseDto of(ResponseCode responseCode, Object data) {
        return ResponseDto.builder()
                .message(responseCode.getMessage())
                .data(data)
                .build();
    }

    public static ResponseDto of(ResponseCode responseCode) {
        return ResponseDto.builder()
                .message(responseCode.getMessage())
                .data(null)
                .build();
    }
}
