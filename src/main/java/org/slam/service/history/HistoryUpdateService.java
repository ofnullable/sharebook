package org.slam.service.history;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;
import org.slam.dto.book.OwnerAnswer;
import org.slam.mapper.book.BookUpdateMapper;
import org.slam.mapper.history.HistorySaveMapper;
import org.slam.mapper.history.HistoryUpdateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.slam.utils.TransactionUtils.isSuccess;

@Service
@Transactional
@AllArgsConstructor
public class HistoryUpdateService {

    private final BookUpdateMapper bookUpdateMapper;
    private final HistorySaveMapper historySaveMapper;
    private final HistoryUpdateMapper historyUpdateMapper;

    public int reservationRequest(Long id, String modifier) {
        return historySaveMapper.insertHistory(Book.builder().id(id).status(BookStatus.ON_RESERVED).modifiedBy(modifier).build());
    }

    public int cancelReservationRequest(Long id, String username) {
        return historyUpdateMapper.cancelReservation(id, username);
    }

    public int returnRequest(Long id, String modifier) {
        return historyUpdateMapper.updateBookHistoryToReturnRequest(Book.builder().id(id).status(BookStatus.RETURN_REQUEST).modifiedBy(modifier).build());
    }

    public int updateToMatchStatus(Book book) {
        // 대여요청 수락 || 반납요청 거절의 경우 ON_LOAN 으로 상태 업데이트
        if (
                (BookStatus.WAIT_FOR_RESPONSE == book.getStatus() && OwnerAnswer.ACCEPT == book.getOwnerAnswer())
                || (BookStatus.RETURN_REQUEST == book.getStatus() && OwnerAnswer.REJECT == book.getOwnerAnswer())
        ) {
            return updateToOnLoan(book);
        } else {
            // 대여요청 거절 || 반납요청 수락의 경우 `Book`의 상태는 AVAILABLE, 'BookHistory'의 상태는 각각 REJECTED, RETURNED 로 변경
            if (BookStatus.WAIT_FOR_RESPONSE == book.getStatus() && OwnerAnswer.REJECT == book.getOwnerAnswer()) {
                return updateHistoryToRejected(book);
            } else if (BookStatus.RETURN_REQUEST == book.getStatus() && OwnerAnswer.ACCEPT == book.getOwnerAnswer()) {
                return updateHistoryToReturned(book);
            }
            return 0;
        }
    }

    private int updateToOnLoan(Book book) {
        return isSuccess(bookUpdateMapper.updateStatus(book), historyUpdateMapper.updateHistory(makeHistoryMatchStatus(book, BookStatus.ON_LOAN)));
    }

    private int updateHistoryToReturned(Book book) {
        return isSuccess(updateToAvailable(book), historyUpdateMapper.updateHistory(makeHistoryMatchStatus(book, BookStatus.RETURNED)));
    }

    private int updateHistoryToRejected(Book book) {
        return isSuccess(updateToAvailable(book), historyUpdateMapper.updateHistory(makeHistoryMatchStatus(book, BookStatus.REJECTED)));
    }

    private int updateToAvailable(Book book) {
        return bookUpdateMapper.updateStatus(book);
    }

    private BookHistory makeHistoryMatchStatus(Book book, BookStatus status) {
        return BookHistory.builder()
                .id(book.getHistories().get(0).getId())
                .modifiedBy(book.getModifiedBy())
                .requestedStatus(status)
                .build();
    }

    /*
    private void updateForAnswerAboutLoan(Book book, String username) {
        if (book.getOwnerAnswer() == OwnerAnswer.ACCEPT) {
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
        if (book.getOwnerAnswer() == OwnerAnswer.ACCEPT) {
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

    */
}
