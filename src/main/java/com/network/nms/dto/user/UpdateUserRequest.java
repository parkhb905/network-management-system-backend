package com.network.nms.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    /** 이메일 **/
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    /** 현재 비밀번호 **/
    private String currentPassword;

    /** 새 비밀번호 **/
    @Size(min = 8, max = 20, message = "비밀번호는 8~20자 이내로 입력해주세요.")
    private String newPassword;

}