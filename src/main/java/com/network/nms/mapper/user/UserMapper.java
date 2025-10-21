package com.network.nms.mapper.user;

import com.network.nms.domain.user.User;
import com.network.nms.dto.user.AdminUserResponse;
import com.network.nms.dto.user.UpdateUserRequest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /** 사용자 등록 **/
    int insertUser(User user);

    /** 로그인 **/
    User findByUsername(String username);

    /** 회원정보 수정 **/
    int updateMyInfo(User user);

    /** 회원탈퇴 **/
    int deleteByUserName(String username);

    /** 사용자 수 조회 **/
    long countUsers();

    /** 사용자 목록 조회 **/
    List<AdminUserResponse> findUsers(@Param("offset") int offset, @Param("size") int size, @Param("totalElements") long totalElements);

    /** 관리자 회원정보 수정 **/
    int updateUser(UpdateUserRequest request);
    
}
