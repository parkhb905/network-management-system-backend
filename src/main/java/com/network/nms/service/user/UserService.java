package com.network.nms.service.user;

import com.network.nms.domain.user.User;
import com.network.nms.dto.common.CommandResponse;
import com.network.nms.dto.user.UpdateUserRequest;
import com.network.nms.exception.CustomException;
import com.network.nms.exception.ErrorCode;
import com.network.nms.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원정보 수정
     * @param username
     * @param request
     * @return
     */
    public CommandResponse updateUser(String username, UpdateUserRequest request) {
        // 기존 회원정보 
        User user = userMapper.findByUsername(username);

        // 이메일 변경 시
        if(request.getEmail() != null && !request.getEmail().isBlank()) {
            user.setEmail(request.getEmail());
        }
        
        // 비밀번호 변경 시
        if(request.getNewPassword() != null && !request.getNewPassword().isBlank()) {
            // 기존 비밀번호 검증
            if(request.getCurrentPassword() == null
                    || !passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                throw new CustomException(ErrorCode.AUTH_INVALID_PASSWORD);
            }
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        int result = userMapper.updateUser(user);
        if(result != 1) {
            throw new CustomException(ErrorCode.DB_FAILED);
        }
        return new CommandResponse(true, result);
    }

    /**
     * 회원탈퇴
     * @param username
     * @return
     */
    public CommandResponse deleteUser(String username) {
        int result = userMapper.deleteByUserName(username);
        if(result != 1) {
            throw new CustomException(ErrorCode.DB_FAILED);
        }
        return new CommandResponse(true, result);
    }

}