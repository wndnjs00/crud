package com.example.crud.util;

import com.example.crud.utils.ApiResponse;
import com.example.crud.utils.ErrorCode;
import io.jsonwebtoken.JwtException;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

// 예외 발생시 적절한 상태코드와 메시지 반환
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("IllegalArgumentException 발생: {}", ex.getMessage());
        String message = ex.getMessage();
        ErrorCode errorCode;

        if (message.contains("이미 존재하는 이메일입니다")) {
            errorCode = ErrorCode.EXIST_EMAIL;
        } else if (message.contains("비밀번호가 일치하지 않습니다")) {
            errorCode = ErrorCode.INVALID_PASSWORD_ERROR;
        } else if (message.contains("올바르지 않은 요청입니다")) {
            errorCode = ErrorCode.BAD_REQUEST_ERROR;
        } else if (message.contains("Refresh Token이 만료되었습니다")) {
            errorCode = ErrorCode.REFRESH_TOKEN_EXPIRED;
        } else if (message.contains("유효하지 않은 Refresh Token입니다")) {
            errorCode = ErrorCode.BAD_REQUEST_ERROR;
        } else {
            errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.ok(ApiResponse.error(errorCode, message));
    }

    // NoSuchElementException 처리
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<String>> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity .ok(ApiResponse.error(ErrorCode.NO_SUCH_USER_ERROR, "존재하지 않는 사용자입니다."));
    }


    // JWT 관련 예외 처리
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<String>> handleJwtException(JwtException ex) {
        logger.error("JwtException 발생: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(ErrorCode.AUTHENTICATION_ERROR, "유효하지 않은 JWT 토큰입니다."));
    }

    // Custom UnauthorizedException 처리
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(UnauthorizedException ex) {
        logger.error("UnauthorizedException 발생: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(ErrorCode.AUTHENTICATION_ERROR, ex.getMessage()));
    }

    // 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        logger.error("Exception 발생: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR, "서버 에러가 발생했습니다."));
    }

    // Hibernate와 완련 예외 (id를 UUID로 바꾸는 과정)
    @ExceptionHandler(StaleObjectStateException.class)
    public ResponseEntity<ApiResponse<String>> handleStaleObjectStateException(StaleObjectStateException ex) {
        logger.error("StaleObjectStateException 발생: {}", ex.getMessage());
        return ResponseEntity.ok(ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR, "데이터 충돌이 발생했습니다. 다시 시도해주세요."));
    }

}

