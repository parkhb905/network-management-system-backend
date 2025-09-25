package com.network.nms.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {
    
    /** 아이디 **/
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String username;

    /** 이메일 **/
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    /** 비밀번호 **/
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

}