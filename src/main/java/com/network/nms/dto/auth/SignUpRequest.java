package com.network.nms.dto.auth;

import lombok.Data;

@Data
public class SignUpRequest {
    
    /** 아이디 **/
    private String username;

    /** 이메일 **/
    private String email;

    /** 비밀번호 **/
    private String password;

}