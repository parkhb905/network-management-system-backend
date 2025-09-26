package com.network.nms.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    /** Access Token **/
    private String accessToken;

    /** Refresh Token **/
    private String refreshToken;

    /** 아이디 **/
    private String username;

}