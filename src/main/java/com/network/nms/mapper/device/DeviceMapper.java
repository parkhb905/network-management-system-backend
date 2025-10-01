package com.network.nms.mapper.device;

import com.network.nms.domain.device.Device;
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

    /** 장비 상세 조회 **/
    DeviceResponse findDevice(@Param("deviceId") Long deviceId);
    
    /** 장비 등록 **/
    int insertDevice(Device device);

    /** 장비 수정 **/
    int updateDevice(Device device);

    /** 장비 삭제 **/
    int deleteDevices(@Param("selectDeviceIds") List<Long> selectDeviceIds);

}