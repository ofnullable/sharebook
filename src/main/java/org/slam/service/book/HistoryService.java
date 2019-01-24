package org.slam.service.book;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;
import org.slam.dto.book.UserAnswer;
import org.slam.mapper.book.BookUpdateMapper;
import org.slam.mapper.book.HistoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class HistoryService {

    private final BookUpdateMapper bookUpdateMapper;
    private final HistoryMapper historyMapper;

    public List<Book> selectMatchStatusHistory(BookStatus status, String username) {
        return historyMapper.selectMatchStatusHistory(status, username);
    }

    public List<BookHistory> selectHistoryById(Long id, String username) {
        return historyMapper.selectHistoryById(id, username);
    }

    public void updateBookHistory(Book book, String username) {
        if (book.getOwnerAnswer() == UserAnswer.ACCEPT) {
            book.setStatus(BookStatus.ON_LOAN);
            book.setModifiedBy(username);
            bookUpdateMapper.updateStatus(book);
            var history = BookHistory.builder()
                    .bookId(book.getId())
                    .requestedStatus(BookStatus.ON_LOAN)
                    .build();
            historyMapper.updateBookHistoryToOnLoan(history);
        } else {
            book.setStatus(BookStatus.AVAILABLE);
            book.setModifiedBy(username);
            bookUpdateMapper.updateStatus(book);
            var history = BookHistory.builder()
                    .bookId(book.getId())
                    .requestedStatus(BookStatus.REJECTED)
                    .build();
            historyMapper.updateBookHistoryToAvailable(history);
            historyMapper.updateBookHistoryOnReservedToWaitForResponse(book.getId());
        }
    }
}
