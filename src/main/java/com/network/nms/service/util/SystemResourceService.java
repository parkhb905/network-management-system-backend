package com.network.nms.service.util;

import com.network.nms.domain.device.Device;
import com.network.nms.mapper.device.DeviceMapper;
import com.network.nms.mapper.util.SystemResourceMapper;
import com.network.nms.util.SystemResourceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemResourceService {

    private final SystemResourceMapper systemResourceMapper;
    private final DeviceMapper deviceMapper;

    // 1분마다 실행
//    @Scheduled(cron = "0 */1 * * * *")
    public void collectSystemResource() {

        List<Device> devices = deviceMapper.findAllActiveDevices();

        if(devices.isEmpty()) {
            return;
        }

        double cpuUsage = SystemResourceUtil.getCpuUsage();
        double memoryUsage = SystemResourceUtil.getMemoryUsage();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        for(Device device : devices) {
            systemResourceMapper.insertSystemResource(device.getDeviceId(), cpuUsage, memoryUsage);
            System.out.printf("[%s] Inserted DEVICE=%d → CPU=%.2f%%, MEM=%.2f%%%n", now, device.getDeviceId(), cpuUsage, memoryUsage);
        }
    }

}