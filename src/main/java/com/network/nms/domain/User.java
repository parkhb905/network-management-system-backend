package com.network.nms.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    /** key **/
    private Long id;

    /** 아이디 **/
    private String username;

    /** 이메일 **/
    private String email;

    /** 비밀번호 **/
    private String password;

    /** 생성일자 **/
    private String createdAt;

    /** 수정일자 **/
    private String updatedAt;

    /** 권한 **/
    private String role;

}