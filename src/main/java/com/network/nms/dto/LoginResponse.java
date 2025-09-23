package com.network.nms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    /** JWT 토큰 (성공 시에만 값 있음) **/
    private String token;

    /** 메세지 **/
    private String message;

}