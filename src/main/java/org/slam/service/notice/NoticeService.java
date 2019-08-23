package org.slam.service.notice;

import lombok.AllArgsConstructor;
import org.slam.dto.account.AccountDto;
import org.slam.dto.book.BookHistory;
import org.slam.mapper.notice.NoticeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public Integer findTotalCount(AccountDto acc) {
        return noticeMapper.findTotalCount(acc);
    }

    public List<BookHistory> findByUsername(String username) {
        return noticeMapper.findByUsername(username);
    }

    public int updateToChecked(Long historyId, String username) {
        return noticeMapper.updateToChecked(historyId, username);
    }
}
