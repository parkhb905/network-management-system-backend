package com.network.nms.mapper.dashboard;

import com.network.nms.dto.dashboard.DeviceCpuUsageResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DashboardMapper {

    /** 장비구분별 장비수 **/
    List<Map<String, Object>> selectDeviceCountByType();

    /** 제조사별 장비수 **/
    List<Map<String, Object>> selectDeviceCountByVendor();

    /** CPU 사용률 TOP5 **/
    List<DeviceCpuUsageResponse> selectTop5CpuUsage();

}