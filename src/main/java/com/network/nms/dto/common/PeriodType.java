package com.network.nms.dto.common;

public enum PeriodType {
    HOURS_24("24h"),
    DAYS_7("7d");
    
    private final String value;
    
    PeriodType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static PeriodType fromValue(String value) {
        for (PeriodType period : values()) {
            if (period.value.equals(value)) {
                return period;
            }
        }
        throw new IllegalArgumentException("Invalid period: " + value);
    }
}
