package com.network.nms.service.auth;

import com.network.nms.domain.User;
import com.network.nms.dto.auth.LoginRequest;
import com.network.nms.dto.auth.LoginResponse;
import com.network.nms.dto.auth.SignUpRequest;
import com.network.nms.mapper.user.UserMapper;
import com.network.nms.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입
     * @param request
     * @return
     */
    public int register(SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // 암호화해서 저장.
                .role("USER")
                .build();

        int result = userMapper.insertUser(user);

        if (result != 1) {
            throw new IllegalStateException("회원가입에 실패했습니다.");
        }

        return result;
    }

    /**
     * 로그인
     * @param request
     * @return
     */
    public LoginResponse login(LoginRequest request) {
        User user = userMapper.findByUsername(request.getUsername());

        // 로그인 실패
        if(user == null) return new LoginResponse(null);
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) return new LoginResponse(null);

        // 로그인 성공 -> JWT 발급
        String token = jwtTokenProvider.generateToken(user.getUsername());

        return new LoginResponse(token);
    }
}