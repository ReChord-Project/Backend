package ReChord.backend.global.exception;

import ReChord.backend.global.common.ResponseCode;
import ReChord.backend.global.common.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseDto> BusinessExceptionHandler(BusinessException e) {
        log.warn(e.getMessage(), e);
        ResponseCode responseCode = e.getResponseCode();
        return ResponseEntity
                .status(responseCode.getStatus())
                .body(ResponseDto.of(responseCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> BusinessExceptionHandler(MethodArgumentNotValidException e) {
        log.warn(e.getMessage(), e);
        return ResponseEntity
                .status(ResponseCode.INVALID_REQUEST.getStatus())
                .body(ResponseDto.of(ResponseCode.INVALID_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> BusinessExceptionHandler(Exception e) {
        log.warn(e.getMessage(), e);
        return ResponseEntity
                .status(ResponseCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(ResponseDto.of(ResponseCode.INTERNAL_SERVER_ERROR));
    }

}
