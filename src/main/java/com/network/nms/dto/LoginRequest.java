package com.network.nms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    /** 아이디 **/
    private String username;

    /** 비밀번호 **/
    private String password;

}