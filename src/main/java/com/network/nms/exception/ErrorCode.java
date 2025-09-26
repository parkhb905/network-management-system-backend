package com.network.nms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 인증/인가 관련 */
    AUTH_USER_NOT_FOUND(1001, "존재하지 않는 사용자입니다.", HttpStatus.UNAUTHORIZED),
    AUTH_INVALID_PASSWORD(1002, "비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    AUTH_EXPIRED_TOKEN(1003, "인증이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    AUTH_FORBIDDEN(1004, "접근이 금지되었습니다.", HttpStatus.FORBIDDEN),

    /* 회원가입/사용자 관련 */
    USERNAME_ALREADY_EXISTS(2001, "이미 사용 중인 아이디입니다.", HttpStatus.CONFLICT),

    /* DB */
    DB_FAILED(9001, "DB 문제로 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    /* 서버 */
    INTERNAL_SERVER_ERROR(9999, "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    
    private final int code;                 // 비즈니스 코드
    private final String message;           // 기본 메시지
    private final HttpStatus status;        // HTTP 상태

}