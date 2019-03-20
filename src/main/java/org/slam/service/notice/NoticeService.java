package org.slam.service.notice;

import lombok.AllArgsConstructor;
import org.slam.dto.account.Account;
import org.slam.mapper.notice.NoticeMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public Integer findTotalCount(Account acc) {
        return noticeMapper.findTotalCount(acc);
    }

}
