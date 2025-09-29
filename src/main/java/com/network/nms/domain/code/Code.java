package com.network.nms.domain.code;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Code {

    /** 코드 아이디 (PK) **/
    private Long codeId;

    /** 그룹 아이디 (FK) **/
    private Long groupId;

    /** 코드 (예: 001-0001) **/
    private String code;

    /** 코드명 (예: CR) **/
    private String codeName;

    /** 생성자 아이디 **/
    private Long createdBy;

    /** 생성 일시 **/
    private LocalDateTime createdAt;

    /** 수정자 아이디 **/
    private Long updatedBy;

    /** 수정 일시 **/
    private LocalDateTime updatedAt;

    /** 삭제 여부 **/
    private Boolean deleted;

    /** 삭제 일시 **/
    private LocalDateTime deletedAt;
    
}