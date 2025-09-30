package com.network.nms.service.device;

import com.network.nms.domain.device.Device;
import com.network.nms.dto.common.CommandResponse;
import com.network.nms.dto.common.PageResponse;
import com.network.nms.dto.common.PagedQueryResponse;
import com.network.nms.dto.common.QueryResponse;
import com.network.nms.dto.device.DeviceRequest;
import com.network.nms.dto.device.DeviceResponse;
import com.network.nms.exception.CustomException;
import com.network.nms.exception.ErrorCode;
import com.network.nms.mapper.device.DeviceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceService {

    private final DeviceMapper deviceMapper;

    /**
     * 장비 목록 조회
     * @param page
     * @param size
     * @return
     */
    public PagedQueryResponse<DeviceResponse> getDevices(int page, int size) {
        int offset = (page - 1) * size;
        long totalElements = deviceMapper.countDevices();
        List<DeviceResponse> content = deviceMapper.findDevices(offset, size, totalElements);
        int totalPages = (int) Math.ceil((double) totalElements / size);

        PageResponse pageInfo = new PageResponse(page, size, totalElements, totalPages);
        return new PagedQueryResponse<>(true, content, pageInfo);
    }

    /**
     * 장비 상세 조회
     * @param deviceId
     * @return
     */
    public QueryResponse<DeviceResponse> getDeviceById(Long deviceId) {
        DeviceResponse response = deviceMapper.findDevice(deviceId);
        return new QueryResponse<>(true, response);
    }

    /**
     * 장비 등록
     * @param request
     * @return
     */
    public CommandResponse createDevice(DeviceRequest request, Long id) {
        Device device = Device.builder()
                .deviceName(request.getDeviceName())
                .deviceTypeId(request.getDeviceTypeId())
                .vendorId(request.getVendorId())
                .createdBy(id)
                .build();
        int result = deviceMapper.insertDevice(device);
        if(result != 1) {
            throw new CustomException(ErrorCode.DB_FAILED);
        }
        return new CommandResponse(true, result);
    }

    public CommandResponse updateDevice(Long deviceId, DeviceRequest request, Long id) {
        Device device = Device.builder()
                .deviceId(deviceId)
                .deviceName(request.getDeviceName())
                .deviceTypeId(request.getDeviceTypeId())
                .vendorId(request.getVendorId())
                .createdBy(id)
                .build();
        int result = deviceMapper.updateDevice(device);
        if(result != 1) {
            throw new CustomException(ErrorCode.DB_FAILED);
        }
        return new CommandResponse(true, result);
    }

}