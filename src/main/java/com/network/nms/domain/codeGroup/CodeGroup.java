package com.network.nms.domain.codeGroup;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CodeGroup {
    
    /** 그룹 아이디 (PK) **/
    private Long groupId;

    /** 그룹 코드 (예: 001) **/
    private String groupCode;

    /** 그룹명 (예: 장비구분) **/
    private String groupName;

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