package com.network.nms.controller.dashboard;

import com.network.nms.dto.common.QueryResponse;
import com.network.nms.service.dashboard.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}