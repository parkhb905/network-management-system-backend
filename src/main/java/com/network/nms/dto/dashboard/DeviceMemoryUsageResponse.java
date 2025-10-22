package com.network.nms.dto.dashboard;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceMemoryUsageResponse {

    /** 장비명 **/
    private String deviceName;

    /** MEMORY 사용률 **/
    private Double avgMemoryUsage;

    /** 수집일시 **/
    private LocalDateTime collected_at;

}