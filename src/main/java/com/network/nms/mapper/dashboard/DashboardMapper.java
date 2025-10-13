package com.network.nms.mapper.dashboard;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DashboardMapper {

    /** 장비구분별 장비수 **/
    List<Map<String, Object>> selectDeviceCountByType();

}