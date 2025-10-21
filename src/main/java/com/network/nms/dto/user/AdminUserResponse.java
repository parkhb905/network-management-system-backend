package com.network.nms.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminUserResponse {

    /** 순번 **/
    private Long rownum;

    /** key **/
    private Long userId;

    /** 아이디 **/
    private String username;

    /** 이름 **/
    private String name;

    /** 이메일 **/
    private String email;

    /** 권한 **/
    private String role;

    /** 생성 일시 **/
    private LocalDateTime createdAt;

    /** 수정 일시 **/
    private LocalDateTime updatedAt;

}