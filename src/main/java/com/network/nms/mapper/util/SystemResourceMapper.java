package com.network.nms.mapper.util;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemResourceMapper {

    int insertSystemResource(@Param("deviceId") Long deviceId, @Param("cpuUsage") double cpuUsage, @Param("memoryUsage") double memoryUsage);

}