package com.network.nms.controller.code;

import com.network.nms.dto.code.CodeResponse;
import com.network.nms.dto.common.QueryResponse;
import com.network.nms.service.code.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/codes")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @GetMapping
    public ResponseEntity<QueryResponse<List<CodeResponse>>> getCodesByGroupCode(@RequestParam String groupCode) {
        QueryResponse response = codeService.getCodesByGroupCode(groupCode);
        return ResponseEntity.ok(response);
    }

}