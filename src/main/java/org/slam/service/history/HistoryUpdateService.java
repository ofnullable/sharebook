package org.slam.service.history;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;
import org.slam.dto.book.UserAnswer;
import org.slam.mapper.book.BookUpdateMapper;
import org.slam.mapper.history.HistoryMapper;
import org.slam.mapper.history.HistoryUpdateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class HistoryUpdateService {

    private final BookUpdateMapper bookUpdateMapper;
    private final HistoryMapper historyMapper;
    private final HistoryUpdateMapper historyUpdateMapper;

    public int reservationRequest(Long id, String modifier) {
        return historyMapper.insertHistory(Book.builder().id(id).status(BookStatus.ON_RESERVED).modifiedBy(modifier).build());
    }

    public int cancelReservationRequest(Long id, String username) {
        return historyUpdateMapper.cancelReservation(id, username);
    }

    public int returnRequest(Long id, String modifier) {
        return historyUpdateMapper.updateBookHistoryToReturnRequest(Book.builder().id(id).status(BookStatus.RETURN_REQUEST).modifiedBy(modifier).build());
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
            historyUpdateMapper.updateBookHistoryToOnLoan(history);
        } else {
            book.setStatus(BookStatus.AVAILABLE);
            var history = makeHistoryMatchStatus(book, BookStatus.REJECTED);
            historyUpdateMapper.updateBookHistoryStatus(history);
            historyUpdateMapper.updateBookHistoryOnReservedToWaitForResponse(book.getId());
        }
        updateBookStatus(book, username);
    }

    private void updateForAnswerAboutReturn(Book book, String username) {
        if (book.getOwnerAnswer() == UserAnswer.ACCEPT) {
            book.setStatus(BookStatus.AVAILABLE);
            var history = makeHistoryMatchStatus(book, BookStatus.ON_LOAN);
            historyUpdateMapper.updateBookHistoryToOnLoan(history);
        } else {
            book.setStatus(BookStatus.ON_LOAN);
            var history = makeHistoryMatchStatus(book, BookStatus.REJECTED);
            historyUpdateMapper.updateBookHistoryStatus(history);
            historyUpdateMapper.updateBookHistoryOnReservedToWaitForResponse(book.getId());
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
