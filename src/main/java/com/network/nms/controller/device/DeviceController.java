package com.network.nms.controller.device;

import com.network.nms.dto.common.PagedQueryResponse;
import com.network.nms.dto.device.DeviceResponse;
import com.network.nms.service.device.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    /**
     * 장비 목록 조회
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public ResponseEntity<PagedQueryResponse<DeviceResponse>> getDevices(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        PagedQueryResponse response = deviceService.getDevices(page, size);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<QueryResponse<DeviceResponse>> getDevice() {
//
//    }

}
