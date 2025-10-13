package com.network.nms.service.log;

import com.network.nms.domain.log.UserAccessLog;
import com.network.nms.dto.common.PageResponse;
import com.network.nms.dto.common.PagedQueryResponse;
import com.network.nms.dto.log.UserAccessLogResponse;
import com.network.nms.mapper.log.UserAccessLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccessLogService {

    private final UserAccessLogMapper userAccessLogMapper;

    /**
     * 로그인 시도 로그 저장
     * @param userId
     * @param username
     * @param result
     * @param ip
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLoginLog(Long userId, String username, String result, String ip) {
        UserAccessLog userAccessLog = UserAccessLog.builder()
                .userId(userId)
                .username(username)
                .result(result)
                .ip(ip)
                .build();

        userAccessLogMapper.insertLoginLog(userAccessLog);
    }

    /**
     * 로그 목록 조회
     * @param page
     * @param size
     * @return
     */
    @Transactional(readOnly = true)
    public PagedQueryResponse<UserAccessLogResponse> getUserAccessLogs(int page, int size) {
        int offset = (page - 1) * size;
        long totalElements = userAccessLogMapper.countUserAccessLogs();
        List<UserAccessLogResponse> content = userAccessLogMapper.findUserAccessLogs(offset, size, totalElements);
        int totalPages = (int) Math.ceil((double) totalElements / size);

        PageResponse pageInfo = new PageResponse(page, size, totalElements, totalPages);
        return new PagedQueryResponse<>(true, content, pageInfo);
    }

}