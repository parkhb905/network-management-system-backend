package com.network.nms.dto.code;

import lombok.Data;

@Data
public class CodeResponse {

    /** 코드 아이디 (PK) **/
    private Long codeId;

    /** 코드 **/
    private String code;

    /** 코드명 **/
    private String codeName;

}