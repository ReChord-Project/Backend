package ReChord.backend.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(200,"Success"),

    // user
    NOT_EXISTED_USER(400,"This user does not exist"),

    ;
    private final int status;
    private final String message;
}
