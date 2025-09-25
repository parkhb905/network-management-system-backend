package com.network.nms.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 등록, 수정, 삭제 공통 응답 Wrapper
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandResponse extends BaseResponse {

    /** 영향받은 행 갯수 **/
    private int rowsAffected;

    public CommandResponse(boolean success, int rowsAffected) {
        super(success);
        this.rowsAffected = rowsAffected;
    }

}