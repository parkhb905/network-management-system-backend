package com.network.nms.controller.user;

import com.network.nms.dto.common.CommandResponse;
import com.network.nms.dto.user.UpdateUserRequest;
import com.network.nms.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원정보 수정
     * @param authentication
     * @param request
     * @return
     */
    @PutMapping("/me")
    public ResponseEntity<CommandResponse> updateUser(Authentication authentication, @RequestBody UpdateUserRequest request) {
        String username = authentication.getName();
        CommandResponse response = userService.updateUser(username, request);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원탈퇴
     * @param authentication
     * @return
     */
    @DeleteMapping("/me")
    public ResponseEntity<CommandResponse> deleteUser(Authentication authentication) {
        String username = authentication.getName();
        CommandResponse response = userService.deleteUser(username);
        return ResponseEntity.ok(response);
    }


}