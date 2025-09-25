package com.network.nms.exception;

import com.network.nms.dto.common.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 스프링 전역 예외 처리 핸들러
 */
@RestControllerAdvice // 컨트롤러 전역에서 발생한 예외를 가로채고, JSON 응답 형태로 클라이언트에게 반환.
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class) // CustomException 예외 발생 시 실행.
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse response = new ErrorResponse(
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.getStatus().value()
        );
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

}