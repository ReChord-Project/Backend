package ReChord.backend.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    // global
    SUCCESS(HttpStatus.OK,"Success."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST,"The request parameters are invalid."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"An internal server error has occurred."),

    // user
    NOT_EXISTED_USER(HttpStatus.NOT_FOUND,"This user does not exist."),
    CANNOT_ADD_SELF(HttpStatus.BAD_REQUEST,"You cannot send a friend request to yourself."),
    ALREADY_FRIEND(HttpStatus.BAD_REQUEST,"You are already friends with this user."),
    ALREADY_REQUESTED(HttpStatus.BAD_REQUEST,"You have already sent a friend request to this user."),
    NOT_EXISTED_REQUEST(HttpStatus.NOT_FOUND,"This request does not exist."),
    NO_PERMISSION(HttpStatus.UNAUTHORIZED,"You do not have permission.");

    ;
    private final HttpStatus status;
    private final String message;
}
