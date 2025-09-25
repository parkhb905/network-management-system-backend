package com.network.nms.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private int code;           // 비즈니스 코드
    private String message;     // 사용자 메시지
    private int status;         // HTTP 상태 코드

}