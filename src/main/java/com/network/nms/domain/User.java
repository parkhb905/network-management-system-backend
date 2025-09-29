package com.network.nms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /** key **/
    private Long id;

    /** 아이디 **/
    private String username;

    /** 이메일 **/
    private String email;

    /** 비밀번호 **/
    private String password;

    /** 생성 일시 **/
    private String createdAt;

    /** 수정 일시 **/
    private String updatedAt;

    /** 권한 **/
    private String role;

    /** 삭제 여부 **/
    private Boolean deleted;

    /** 삭제 일시 **/
    private LocalDateTime deletedAt;
    
}