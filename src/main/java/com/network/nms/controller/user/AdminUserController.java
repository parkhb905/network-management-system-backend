package com.network.nms.controller.user;

import com.network.nms.dto.common.PagedQueryResponse;
import com.network.nms.dto.user.AdminUserResponse;
import com.network.nms.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final UserService userService;

    /**
     * 사용자 목록 조회
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public ResponseEntity<PagedQueryResponse<AdminUserResponse>> getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PagedQueryResponse response = userService.getUsers(page, size);
        return ResponseEntity.ok(response);
    }

}