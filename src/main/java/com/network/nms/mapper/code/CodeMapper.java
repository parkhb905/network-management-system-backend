package com.network.nms.mapper.code;

import com.network.nms.dto.code.CodeResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper {

    /** 그룹 코드로 해당 그룹에 속한 코드 목록 조회 **/
    List<CodeResponse> findCodesByGroupCode(String groupCode);

}