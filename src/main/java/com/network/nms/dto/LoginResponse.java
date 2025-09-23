package com.network.nms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    
    /** 성공여부 **/
    private boolean success;

    /** 메세지 **/
    private String message;

}