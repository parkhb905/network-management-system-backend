package com.network.nms.service.dashboard;

import com.network.nms.dto.common.QueryResponse;
import com.network.nms.dto.dashboard.DeviceCpuUsageResponse;
import com.network.nms.mapper.dashboard.DashboardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardMapper dashboardMapper;

    /**
     * 장비구분별 장비수
     * @return
     */
    public QueryResponse<List<Map<String, Object>>> getDeviceCountByType() {
        List<Map<String, Object>> map = dashboardMapper.selectDeviceCountByType();
        return new QueryResponse<>(true, map);
    };

    /**
     * 제조사별 장비수
     * @return
     */
    public QueryResponse<List<Map<String, Object>>> getDeviceCountByVendor() {
        List<Map<String, Object>> map = dashboardMapper.selectDeviceCountByVendor();
        return new QueryResponse<>(true, map);
    };

    /**
     * CPU 사용률 TOP5
     * @return
     */
    public QueryResponse<List<DeviceCpuUsageResponse>> getTop5CpuUsage() {
        List<DeviceCpuUsageResponse> list = dashboardMapper.selectTop5CpuUsage();
        return new QueryResponse<>(true, list);
    };

}