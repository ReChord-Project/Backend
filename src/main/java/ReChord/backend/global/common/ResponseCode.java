package ReChord.backend.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    // global
    SUCCESS(HttpStatus.OK,"Success"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "The request parameters are invalid."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error has occurred."),

    // user
    NOT_EXISTED_USER(HttpStatus.NOT_FOUND,"This user does not exist"),

    ;
    private final HttpStatus status;
    private final String message;
}
