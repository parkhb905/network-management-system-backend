package com.network.nms.dto.device;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceRequest {

    /** 장비명 **/
    @NotBlank(message = "장비명은 필수입니다.")
    private String deviceName;

    /** 장비구분 코드 아이디 **/
    @NotNull(message = "장비구분은 필수입니다.")
    private Long deviceTypeId;

    /** 제조사 코드 아이디 **/
    @NotNull(message = "제조사는 필수입니다.")
    private Long vendorId;

}