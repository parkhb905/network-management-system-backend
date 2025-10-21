package com.network.nms.dto.user;

import com.network.nms.domain.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    /** key **/
    private Long userId;
    
    /** 아이디 **/
    private String username;

    /** 이름 **/
    private String name;

    /** 이메일 **/
    private String email;

    /** 권한 **/
    private String role;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    
}
