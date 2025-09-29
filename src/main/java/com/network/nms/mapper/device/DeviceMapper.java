package com.network.nms.mapper.device;

import com.network.nms.dto.device.DeviceResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeviceMapper {

    /** 장비 목록 조회 **/
    List<DeviceResponse> findDevices(@Param("offset") int offset, @Param("size") int size, @Param("totalElements") long totalElements);

    /** 장비 총 개수 조회 **/
    long countDevices();

}