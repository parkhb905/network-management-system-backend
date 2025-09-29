package com.network.nms.dto.device;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceResponse {

    /** 순번 **/
    private Long rownum;

    /** 장비 아이디 **/
    private Long deviceId;

    /** 장비명 **/
    private String deviceName;

    /** 장비종류 코드 아이디 **/
    private Long deviceTypeId;

    /** 장비종류명 **/
    private String deviceTypeName;

    /** 제조사 코드 아이디 **/
    private Long vendorId;

    /** 제조사명 **/
    private String vendorName;

    /** 생성한 사용자명 **/
    private String createdByName;

    /** 생성 일시 **/
    private LocalDateTime createdAt;

}