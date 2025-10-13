package com.network.nms.controller.log;

import com.network.nms.domain.log.UserAccessLog;
import com.network.nms.dto.common.PagedQueryResponse;
import com.network.nms.service.log.UserAccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final UserAccessLogService userAccessLogService;

    @GetMapping
    public ResponseEntity<PagedQueryResponse<UserAccessLog>> getUserAccessLogs(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        PagedQueryResponse response = userAccessLogService.getUserAccessLogs(page, size);
        return ResponseEntity.ok(response);
    }

}