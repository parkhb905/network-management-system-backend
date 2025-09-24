package com.network.nms.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 조회 공통 응답 Wrapper
 * @param <T>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResponse<T> extends BaseResponse {

    /** 조회 결과 데이터 **/
    private T data;

    public QueryResponse(boolean success, T data) {
        super(success);
        this.data = data;
    }
}