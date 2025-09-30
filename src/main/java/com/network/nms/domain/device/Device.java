package com.network.nms.domain.device;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    /** 장비 아이디 **/
    private Long deviceId;

    /** 장비명 **/
    private String deviceName;

    /** 장비구분 코드 아이디 **/
    private Long deviceTypeId;

    /** 제조사 코드 아이디 **/
    private Long vendorId;

    /** 생성자 아이디 **/
    private Long createdBy;

    /** 생성 일시 **/
    private LocalDateTime createdAt;

}