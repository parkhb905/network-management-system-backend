package com.network.nms.service.auth;

import com.network.nms.domain.User;
import com.network.nms.dto.auth.LoginRequest;
import com.network.nms.dto.auth.LoginResponse;
import com.network.nms.dto.auth.SignUpRequest;
import com.network.nms.dto.auth.TokenResponse;
import com.network.nms.dto.common.CommandResponse;
import com.network.nms.dto.user.UserResponse;
import com.network.nms.exception.CustomException;
import com.network.nms.exception.ErrorCode;
import com.network.nms.mapper.user.UserMapper;
import com.network.nms.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    /**
     * 회원가입
     * @param request
     * @return
     */
    public CommandResponse register(SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // 암호화해서 저장.
                .role("USER")
                .build();

        try {
            int result = userMapper.insertUser(user);

            if (result != 1) {
                throw new CustomException(ErrorCode.DB_FAILED);
            }

            return new CommandResponse(true, result);
        } catch(DuplicateKeyException e) {
            throw new CustomException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }

    }

    /**
     * 로그인
     * @param request
     * @return
     */
    public LoginResponse login(LoginRequest request) {
        User user = userMapper.findByUsername(request.getUsername());

        if(user == null) throw new CustomException(ErrorCode.AUTH_USER_NOT_FOUND);
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new CustomException(ErrorCode.AUTH_INVALID_PASSWORD); // encode() 값 달라질 수 있으므로 matches()로 비교.

        // 로그인 성공 -> JWT 발급
        String accessToken = jwtTokenProvider.generateAccessToken(user.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUsername());

        return new LoginResponse(accessToken, refreshToken, user.getUsername(), user.getEmail());
    }

    public TokenResponse refresh(String refreshToken) {
        // refreshToken에서 username 추출
        String username = jwtTokenProvider.getUsernameFromToken(refreshToken);

        // userDetails 조회
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 토큰 유효성 검증
        if(!jwtTokenProvider.validateToken(refreshToken, userDetails)) throw new RuntimeException("Invalid refresh token");

        // 새 accessToken 발급
        String newAccessToken = jwtTokenProvider.generateAccessToken(username);

        return new TokenResponse(
                newAccessToken,
                refreshToken
        );
    }

    public UserResponse getMyInfo(String username) {
        User user = userMapper.findByUsername(username);
        return UserResponse.from(user);
    }

}