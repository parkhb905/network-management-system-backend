package com.network.nms.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    /** key **/
    private Long userId;

    /** Access Token **/
    private String accessToken;

    /** Refresh Token **/
    private String refreshToken;

    /** 아이디 **/
    private String username;

    /** 이메일 **/
    private String email;

    /** 권한 **/
    private String role;

}