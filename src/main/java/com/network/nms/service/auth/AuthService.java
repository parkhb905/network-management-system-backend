package com.network.nms.service.auth;

import com.network.nms.domain.user.User;
import com.network.nms.dto.auth.LoginRequest;
import com.network.nms.dto.auth.LoginResponse;
import com.network.nms.dto.auth.SignUpRequest;
import com.network.nms.dto.auth.TokenResponse;
import com.network.nms.dto.common.CommandResponse;
import com.network.nms.dto.user.UserResponse;
import com.network.nms.exception.CustomException;
import com.network.nms.exception.ErrorCode;
import com.network.nms.mapper.log.UserAccessLogMapper;
import com.network.nms.mapper.user.UserMapper;
import com.network.nms.security.JwtTokenProvider;
import com.network.nms.service.log.UserAccessLogService;
import com.network.nms.util.NetworkUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final UserAccessLogService userAccessLogService;
    private final UserAccessLogMapper userAccessLogMapper;

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
    public LoginResponse login(HttpServletRequest httpServletRequest, LoginRequest request) {
        String ip = NetworkUtil.getClientIp(httpServletRequest);

        User user = userMapper.findByUsername(request.getUsername());

        if(user == null) throw new CustomException(ErrorCode.AUTH_USER_NOT_FOUND);

        // encode() 값 달라질 수 있으므로 matches()로 비교.
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            userAccessLogService.saveLoginLog(user.getUserId(), request.getUsername(), "FAIL", ip);
            throw new CustomException(ErrorCode.AUTH_INVALID_PASSWORD);
        }

        // 로그인 성공 -> JWT 발급
        userAccessLogService.saveLoginLog(user.getUserId(), request.getUsername(), "SUCCESS", ip);

        String accessToken = jwtTokenProvider.generateAccessToken(user.getUsername());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getUsername());

        return new LoginResponse(user.getUserId(), accessToken, refreshToken, user.getUsername(), user.getEmail(), user.getRole());
    }

    /**
     * 로그아웃
     * @param userId
     * @return
     */
    public CommandResponse logout(long userId) {
        int result = userAccessLogMapper.updateLogoutAt(userId);

        if (result != 1) {
            throw new CustomException(ErrorCode.DB_FAILED);
        }

        return new CommandResponse(true, result);
    }

    /**
     * 액세스토큰 재발급
     * @param refreshToken
     * @return
     */
    public TokenResponse refresh(String refreshToken) {
        // 토큰에서 username 추출
        String username = jwtTokenProvider.getUsernameFromToken(refreshToken);
        
        // 사용자 존재 여부 검증
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (Exception e) {
            // userDetailsService에서 UsernameNotFoundException 발생 시
            throw new CustomException(ErrorCode.AUTH_USER_NOT_FOUND);
        }

        // 토큰 유효성 검증
        if(!jwtTokenProvider.validateTokenOnly(refreshToken)) throw new RuntimeException("Invalid refresh token");

        // 새 accessToken 발급
        String newAccessToken = jwtTokenProvider.generateAccessToken(username);

        return new TokenResponse(
                newAccessToken,
                refreshToken
        );
    }

    /**
     * 사용자 정보 갱신
     * @param username
     * @return
     */
    public UserResponse getMyInfo(String username) {
        User user = userMapper.findByUsername(username);
        return UserResponse.from(user);
    }

}