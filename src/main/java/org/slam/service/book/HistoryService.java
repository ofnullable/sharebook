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

    public void updateBookHistory(Book book, String username) {
        // TODO: make method for each status and ownerAnswer
        /*
        if (BookStatus.WAIT_FOR_RESPONSE == status) {
            updateForAnswerAboutLoan(book, username);
        } else if (BookStatus.RETURN_REQUEST == status) {
            updateForAnswerAboutReturn(book, username);
        }
        */
    }

    private void updateForAnswerAboutLoan(Book book, String username) {
        if (book.getOwnerAnswer() == UserAnswer.ACCEPT) {
            book.setStatus(BookStatus.ON_LOAN);
            var history = makeHistoryMatchStatus(book, BookStatus.ON_LOAN);
            historyMapper.updateBookHistoryToOnLoan(history);
        } else {
            book.setStatus(BookStatus.AVAILABLE);
            var history = makeHistoryMatchStatus(book, BookStatus.REJECTED);
            historyMapper.updateBookHistoryStatus(history);
            historyMapper.updateBookHistoryOnReservedToWaitForResponse(book.getId());
        }
        updateBookStatus(book, username);
    }

    private void updateForAnswerAboutReturn(Book book, String username) {
        if (book.getOwnerAnswer() == UserAnswer.ACCEPT) {
            book.setStatus(BookStatus.AVAILABLE);
            var history = makeHistoryMatchStatus(book, BookStatus.ON_LOAN);
            historyMapper.updateBookHistoryToOnLoan(history);
        } else {
            book.setStatus(BookStatus.ON_LOAN);
            var history = makeHistoryMatchStatus(book, BookStatus.REJECTED);
            historyMapper.updateBookHistoryStatus(history);
            historyMapper.updateBookHistoryOnReservedToWaitForResponse(book.getId());
        }
    }

    private void updateBookStatus(Book book, String username) {
        book.setModifiedBy(username);
        bookUpdateMapper.updateStatus(book);
    }

    private void updateBookHistoryStatus(BookHistory history) {

    }

    // For next request
    private void updateNextBookHistoryStatus(Long bookId) {

    }

    private BookHistory makeHistoryMatchStatus(Book book, BookStatus status) {
        return BookHistory.builder()
                .id(book.getHistories().get(0).getId())
                .requestedStatus(status)
                .build();
    }

}
