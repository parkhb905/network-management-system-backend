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

    /** 생성 일시 **/
    private LocalDateTime createdAt;

}