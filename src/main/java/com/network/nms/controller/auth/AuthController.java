package com.network.nms.controller.auth;

import com.network.nms.dto.auth.LoginRequest;
import com.network.nms.dto.auth.LoginResponse;
import com.network.nms.dto.auth.SignUpRequest;
import com.network.nms.dto.common.CommandResponse;
import com.network.nms.dto.user.UserResponse;
import com.network.nms.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 회원가입
     * @param request
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<CommandResponse> signup(@Valid @RequestBody SignUpRequest request) {
        CommandResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    /**
     * 로그인
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse loginResponse = authService.login(request);
        return ResponseEntity.ok(loginResponse);
    }

    /**
     * accessToken 재발급
     * @param refreshToken
     * @return
     */
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody String refreshToken) {
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }

    /**
     * 사용자 정보 갱신
     * @param authentication
     * @return
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyInfo(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(authService.getMyInfo(username));
    }

}