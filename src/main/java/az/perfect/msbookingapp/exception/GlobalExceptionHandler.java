package az.perfect.msbookingapp.exception;

import az.perfect.msbookingapp.model.constant.ErrorCode;
import az.perfect.msbookingapp.model.dto.response.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalResponse> notFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GlobalResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(ErrorCode.NOT_FOUND)
                .errorMessage(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .build());

    }
}
