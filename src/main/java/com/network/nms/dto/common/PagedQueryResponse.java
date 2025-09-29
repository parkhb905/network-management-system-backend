package com.network.nms.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagedQueryResponse<T> extends BaseResponse {

    /** 조회 결과 리스트 **/
    private List<T> content;

    /** 페이징 정보 **/
    private PageResponse pageInfo;

    public PagedQueryResponse(boolean success, List<T> content, PageResponse pageInfo) {
        super(success);
        this.content = content;
        this.pageInfo = pageInfo;
    }

}