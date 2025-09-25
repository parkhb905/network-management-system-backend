package com.network.nms.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // 예외 발생 시 로그에 ex.getMessage() 찍힘.
        this.errorCode = errorCode;
    }

}