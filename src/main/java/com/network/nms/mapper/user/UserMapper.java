package com.network.nms.mapper.user;

import com.network.nms.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /** 사용자 등록 **/
    int insertUser(User user);

    /** 로그인 **/
    User findByUsername(String username);

    /** 회원정보 수정 **/
    int updateUser(User user);

    /** 회원탈퇴 **/
    int deleteByUserName(String username);

}
