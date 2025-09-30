package com.network.nms.controller.device;

import com.network.nms.dto.common.CommandResponse;
import com.network.nms.dto.common.PagedQueryResponse;
import com.network.nms.dto.device.DeviceRequest;
import com.network.nms.dto.device.DeviceResponse;
import com.network.nms.security.CustomUserDetails;
import com.network.nms.service.device.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 장비 등록
     * @param request
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<CommandResponse> createDevice(@Valid @RequestBody DeviceRequest request, @AuthenticationPrincipal CustomUserDetails user) {
        CommandResponse response = deviceService.createDevice(request, user.getId());
        return ResponseEntity.ok(response);
    }

}
