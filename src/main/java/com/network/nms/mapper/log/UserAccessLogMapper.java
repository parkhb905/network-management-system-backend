package com.network.nms.mapper.log;

import com.network.nms.domain.log.UserAccessLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAccessLogMapper {

    /** 로그인 시도 로그 **/
    void insertLoginLog(UserAccessLog userAccessLog);

    /** 로그아웃 로그 **/
    void updateLogoutAt(@Param("username") String username);

}