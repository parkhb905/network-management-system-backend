package com.network.nms.service.codeGroup;

import com.network.nms.mapper.codeGroup.CodeGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CodeGroupService {

    private final CodeGroupMapper codeGroupMapper;

}