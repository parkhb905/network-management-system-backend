package com.network.nms.dto.dashboard;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceCpuUsageResponse {

    /** 장비명 **/
    private String deviceName;

    /** CPU 사용률 **/
    private Double avgCpuUsage;

    /** 수집일시 **/
    private LocalDateTime collected_at;

}