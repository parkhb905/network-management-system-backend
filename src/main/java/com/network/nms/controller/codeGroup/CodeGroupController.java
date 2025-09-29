package com.network.nms.controller.codeGroup;

import com.network.nms.service.codeGroup.CodeGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/codeGroup")
@RequiredArgsConstructor
public class CodeGroupController {

    private final CodeGroupService codeGroupService;

}