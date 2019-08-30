package org.slam.publicshare.service.history;

import lombok.AllArgsConstructor;
import org.slam.publicshare.dto.book.Book;
import org.slam.publicshare.dto.book.BookHistory;
import org.slam.publicshare.dto.book.BookStatus;
import org.slam.publicshare.dto.common.Paginator;
import org.slam.publicshare.mapper.history.HistorySelectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class HistoryService {

    private final HistorySelectMapper historySelectMapper;

    public Map<String, Object> findMatchStatusHistory(BookStatus status, Paginator paginator) {
        var resultMap = new HashMap<String, Object>();
        paginator.setTotal(historySelectMapper.findTotalCount(status, paginator));

        resultMap.put("paginator", paginator);
        resultMap.put("bookList", historySelectMapper.findMatchStatusHistory(status, paginator));

        return resultMap;
    }

    public List<BookHistory> findHistoryByBookId(Long bookId, String username) {
        return historySelectMapper.findHistoryByBookId(bookId, username);
    }

    public Book findHistoryDetailsByBookId(Long bookId, Paginator paginator) {
        var details = historySelectMapper.findHistoryDetailsByBookId(bookId, paginator);
        if (!details.getCreatedBy().equals(paginator.getUsername())) {
            details.setHistories(
                    details.getHistories().stream()
                            .filter( h -> h.getRequestedUser().equals(paginator.getUsername()) )
                            .collect(Collectors.toList())
            );
        }
        return details;
    }

    public List<BookHistory> findBookRequestHistoryById(Long id, String username) {
        return historySelectMapper.findBookRequestHistoryById(id, username);
    }

}
