package com.network.nms.service.code;

import com.network.nms.dto.code.CodeResponse;
import com.network.nms.dto.common.QueryResponse;
import com.network.nms.mapper.code.CodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CodeService {

    private final CodeMapper codeMapper;

    public QueryResponse<List<CodeResponse>> getCodesByGroupCode(String groupCode) {
        List<CodeResponse> list = codeMapper.findCodesByGroupCode(groupCode);
        return new QueryResponse<>(true, list);
    }

}