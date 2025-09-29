package com.network.nms.service.code;

import com.network.nms.mapper.code.CodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CodeService {

    private final CodeMapper codeMapper;

}