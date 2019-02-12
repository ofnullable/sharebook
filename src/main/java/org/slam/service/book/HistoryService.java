package org.slam.service.book;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;
import org.slam.dto.book.UserAnswer;
import org.slam.dto.common.Paginator;
import org.slam.mapper.book.BookUpdateMapper;
import org.slam.mapper.book.HistoryMapper;
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

    private final BookUpdateMapper bookUpdateMapper;
    private final HistoryMapper historyMapper;

    public Map<String, Object> selectMatchStatusHistory(BookStatus status, Paginator paginator) {
        var resultMap = new HashMap<String, Object>();
        paginator.setTotal(historyMapper.findTotalCount(status, paginator));

        resultMap.put("paginator", paginator);
        resultMap.put("bookList", historyMapper.selectMatchStatusHistory(status, paginator));

        return resultMap;
    }

    public List<BookHistory> selectHistoryByBookId(Long bookId, String username) {
        return historyMapper.selectHistoryByBookId(bookId, username);
    }

    public Book selectHistoryDetailsByBookId(Long bookId, Paginator paginator) {
        var details = historyMapper.selectHistoryDetailsByBookId(bookId, paginator);
        if (!details.getCreatedBy().equals(paginator.getUsername())) {
            details.setHistories(
                    details.getHistories().stream()
                            .filter( h -> h.getRequestedUser().equals(paginator.getUsername()) )
                            .collect(Collectors.toList())
            );
        }
        return details;
    }

    public List<BookHistory> selectBookRequestHistoryById(Long id, String username) {
        return historyMapper.selectBookRequestHistoryById(id, username);
    }

    public void updateBookHistory(Book book, String username) {
        if (book.getOwnerAnswer() == UserAnswer.ACCEPT) {
            book.setStatus(BookStatus.ON_LOAN);
            book.setModifiedBy(username);
            bookUpdateMapper.updateStatus(book);
            var history = BookHistory.builder()
                    .id(book.getHistories().get(0).getId())
                    .requestedStatus(BookStatus.ON_LOAN)
                    .build();
            historyMapper.updateBookHistoryToOnLoan(history);
        } else {
            book.setStatus(BookStatus.AVAILABLE);
            book.setModifiedBy(username);
            bookUpdateMapper.updateStatus(book);
            var history = BookHistory.builder()
                    .id(book.getHistories().get(0).getId())
                    .requestedStatus(BookStatus.REJECTED)
                    .build();
            historyMapper.updateBookHistoryToAvailable(history);
            historyMapper.updateBookHistoryOnReservedToWaitForResponse(book.getId());
        }
    }

}
