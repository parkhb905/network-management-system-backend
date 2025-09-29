package com.network.nms.service.device;

import com.network.nms.dto.common.PageResponse;
import com.network.nms.dto.common.PagedQueryResponse;
import com.network.nms.dto.device.DeviceResponse;
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

}