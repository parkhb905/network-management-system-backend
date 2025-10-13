package com.network.nms.dto.log;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAccessLogResponse {

    /** 순번 **/
    private Long rownum;

    /** 로그인 시도 아이디 **/
    private String username;

    /** 로그인 시도 결과 (SUCCESS / FAIL) **/
    private String result;

    /** 로그인 시도 IP **/
    private String ip;

    /** 로그인 시도 일시 **/
    private LocalDateTime attemptAt;

    /** 로그인 일시 **/
    private LocalDateTime loginAt;

    /** 로그아웃 일시 **/
    private LocalDateTime logoutAt;

}