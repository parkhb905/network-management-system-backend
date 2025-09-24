package com.network.nms.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    /** JWT 토큰 (성공 시에만 값 있음) **/
    private String token;

}