package com.network.nms.util;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class SystemResourceUtil {

    public static double getCpuUsage() {
        OperatingSystemMXBean osBean =
                (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 시스템 전체 CPU 사용률 (0.0 ~ 1.0)
        return osBean.getSystemCpuLoad() * 100;
    }

    public static long getUsedMemory() {
        OperatingSystemMXBean osBean =
                (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long total = osBean.getTotalPhysicalMemorySize();
        long free = osBean.getFreePhysicalMemorySize();
        return total - free;
    }

    public static long getTotalMemory() {
        OperatingSystemMXBean osBean =
                (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osBean.getTotalPhysicalMemorySize();
    }

    public static double getMemoryUsage() {
        long used = getUsedMemory();
        long total = getTotalMemory();
        return (double) used / total * 100;
    }
}