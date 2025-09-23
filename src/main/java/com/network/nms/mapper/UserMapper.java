package com.network.nms.mapper;

import com.network.nms.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /** 사용자 등록 **/
    int insertUser(User user);

    /** 로그인 **/
    User findByUsername(String username);

}
