package com.network.nms.service.dashboard;

import com.network.nms.dto.common.QueryResponse;
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

}