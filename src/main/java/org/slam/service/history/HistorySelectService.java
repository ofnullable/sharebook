package org.slam.service.history;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;
import org.slam.dto.common.Paginator;
import org.slam.mapper.book.BookUpdateMapper;
import org.slam.mapper.history.HistoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class HistorySelectService {

    private final BookUpdateMapper bookUpdateMapper;
    private final HistoryMapper historyMapper;

    public Map<String, Object> findMatchStatusHistory(BookStatus status, Paginator paginator) {
        var resultMap = new HashMap<String, Object>();
        paginator.setTotal(historyMapper.findTotalCount(status, paginator));

        resultMap.put("paginator", paginator);
        resultMap.put("bookList", historyMapper.findMatchStatusHistory(status, paginator));

        return resultMap;
    }

    public List<BookHistory> findHistoryByBookId(Long bookId, String username) {
        return historyMapper.findHistoryByBookId(bookId, username);
    }

    public Book findHistoryDetailsByBookId(Long bookId, Paginator paginator) {
        var details = historyMapper.findHistoryDetailsByBookId(bookId, paginator);
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
        return historyMapper.findBookRequestHistoryById(id, username);
    }

}
