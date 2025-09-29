package com.network.nms.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse {

    /** 현재 페이지 번호 **/
    private int page;

    /** 페이지 크기 **/
    private int size;

    /** 전체 데이터 개수 **/
    private long totalElements;

    /** 전체 페이지 수 **/
    private int totalPages;

}