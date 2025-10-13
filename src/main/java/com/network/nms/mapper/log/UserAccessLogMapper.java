package com.network.nms.mapper.log;

import com.network.nms.domain.log.UserAccessLog;
import com.network.nms.dto.log.UserAccessLogResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAccessLogMapper {

    /** 로그인 시도 로그 **/
    void insertLoginLog(UserAccessLog userAccessLog);

    /** 로그아웃 로그 **/
    int updateLogoutAt(@Param("userId") Long userId);

    /** 로그 목록 조회 **/
    List<UserAccessLogResponse> findUserAccessLogs(@Param("offset") int offset, @Param("size") int size, @Param("totalElements") long totalElements);

    /** 로그 총 건수 조회 **/
    long countUserAccessLogs();

}