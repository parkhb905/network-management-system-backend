package com.network.nms.controller.dashboard;

import com.network.nms.dto.common.PeriodType;
import com.network.nms.dto.common.QueryResponse;
import com.network.nms.dto.dashboard.DeviceCpuUsageResponse;
import com.network.nms.dto.dashboard.DeviceMemoryUsageResponse;
import com.network.nms.service.dashboard.DashboardService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    /**
     * 장비구분별 장비수
     * @return
     */
    @GetMapping("/devices/type")
    public ResponseEntity<QueryResponse<List<Map<String, Object>>>> getDeviceCountByType() {
        QueryResponse response = dashboardService.getDeviceCountByType();
        return ResponseEntity.ok(response);
    }

    /**
     * 제조사별 장비수
     * @return
     */
    @GetMapping("/devices/vendor")
    public ResponseEntity<QueryResponse<List<Map<String, Object>>>> getDeviceCountByVendor() {
        QueryResponse response = dashboardService.getDeviceCountByVendor();
        return ResponseEntity.ok(response);
    }

    /**
     * CPU 사용률 TOP5
     * @param period 조회 기간 (24h, 7d)
     * @return
     */
    @GetMapping("/resource/cpu-top5")
    public ResponseEntity<QueryResponse<List<DeviceCpuUsageResponse>>> getTop5CpuUsage(
            @RequestParam(defaultValue = "24h") String period
    ) {
        PeriodType periodType = PeriodType.fromValue(period);
        QueryResponse response = dashboardService.getTop5CpuUsage(periodType); 
        return ResponseEntity.ok(response);
    }

    /**
     * MEMORY 사용률 TOP5
     * @param period 조회 기간 (24h, 7d)
     * @return
     */
    @GetMapping("/resource/memory-top5")
    public ResponseEntity<QueryResponse<List<DeviceMemoryUsageResponse>>> getTop5MemoryUsage(
            @RequestParam(defaultValue = "24h") String period
    ) {
        PeriodType periodType = PeriodType.fromValue(period);
        QueryResponse response = dashboardService.getTop5MemoryUsage(periodType);
        return ResponseEntity.ok(response);
    }

}