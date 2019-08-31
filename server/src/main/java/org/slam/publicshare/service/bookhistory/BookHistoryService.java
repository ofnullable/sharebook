package org.slam.publicshare.service.bookhistory;

import lombok.AllArgsConstructor;
import org.slam.publicshare.dto.book.Book;
import org.slam.publicshare.dto.book.BookStatus;
import org.slam.publicshare.dto.book.OwnerAnswer;
import org.slam.publicshare.mapper.book.BookUpdateMapper;
import org.slam.publicshare.mapper.history.HistorySaveMapper;
import org.slam.publicshare.mapper.history.HistoryUpdateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.slam.publicshare.utils.Builders.*;
import static org.slam.publicshare.utils.TransactionUtils.isSuccess;

@Service
@Transactional
@AllArgsConstructor
public class BookHistoryService {

    private final BookUpdateMapper bookUpdateMapper;
    private final HistorySaveMapper historySaveMapper;
    private final HistoryUpdateMapper historyUpdateMapper;

    // WAIT_FOR_RESPONSE, ON_RESERVED
    public int saveNewRequest(Book book) {
        if (BookStatus.WAIT_FOR_RESPONSE == book.getStatus()) {
            return saveLoanRequest(book);
        } else {
            return saveReservationRequest(book);
        }
    }

    private int saveLoanRequest(Book book) {
        return isSuccess(
                bookUpdateMapper.updateStatus(book),
                historySaveMapper.insertHistory(buildHistory(book))
        );
    }

    private int saveReservationRequest(Book book) {
        return historySaveMapper.insertHistory(buildHistory(book));
    }

    // RETURN_REQUEST, CANCELED
    public int updateToMatchRequest(Book book) {
        if (BookStatus.RETURN_REQUEST == book.getStatus()) {
            return updateToReturnRequest(book);
        } else {
            // conditional update book, update history
            return updateToCanceled(book);
        }
    }

    private int updateToReturnRequest(Book book) {
        return isSuccess(
                bookUpdateMapper.updateStatus(book),
                historyUpdateMapper.updateHistory(buildHistoryWithHistoryId(book))
        );
    }

    // origin status: RETURN_REQUEST, ON_RESERVED, WAIT_FOR_RESPONSE
    private int updateToCanceled(Book book) {
        if (BookStatus.RETURN_REQUEST == book.getOriginStatus()) {
            // update book && history to ON_LOAN
            return updateToOnLoan(book);
        } else {
            // conditional update book && history to AVAILABLE or WAIT_FOR_RESPONSE and CANCELED
            return updateHistoryToCanceled(book);
        }
    }

    private int updateToOnLoan(Book book) {
        return isSuccess(
                bookUpdateMapper.updateStatus(buildBook(book, BookStatus.ON_LOAN)),
                historyUpdateMapper.updateHistory(buildHistoryWithHistoryId(book, BookStatus.ON_LOAN))
        );
    }

    private int updateHistoryToCanceled(Book book) {
        if (BookStatus.ON_RESERVED == book.getOriginStatus()) {
            return historyUpdateMapper.updateHistory(buildHistoryWithHistoryId(book));
        } else { // book.getOriginStatus == BookStatus.WAIT_FOR_RESPONSE
            return isSuccess(
                    bookUpdateMapper.conditionalUpdateStatus(book),
                    historyUpdateMapper.conditionalUpdateHistory(buildHistoryWithHistoryId(book, BookStatus.CANCELED))
            );
        }
    }

    // WAIT_FOR_RESPONSE, RETURN_REQUEST
    // ACCEPT, REJECT
    public int updateToMatchResponse(Book book) {
        if (isShouldBeOnLoan(book)) {
            // 대여요청 수락 || 반납요청 거절의 경우 ON_LOAN 으로 상태 업데이트
            return updateToOnLoan(book);
        } else {
            // 대여요청 거절 || 반납요청 수락의 경우
            // `Book`의 상태는 AVAILABLE 또는 WAIT_FOR_RESPONSE,
            // 'BookHistory'의 상태는 각각 REJECTED, RETURNED 로 변경
            return conditionalUpdate(book);
        }
    }

    private boolean isShouldBeOnLoan(Book book) {
        if (BookStatus.WAIT_FOR_RESPONSE == book.getStatus() && OwnerAnswer.ACCEPT == book.getOwnerAnswer()) {
            return true;
        } else {
            return BookStatus.RETURN_REQUEST == book.getStatus() && OwnerAnswer.REJECT == book.getOwnerAnswer();
        }
    }

    private int conditionalUpdate(Book book) {
        BookStatus status = BookStatus.WAIT_FOR_RESPONSE == book.getStatus() && OwnerAnswer.REJECT == book.getOwnerAnswer() ?
                BookStatus.REJECTED : BookStatus.RETURNED;
        return isSuccess(
                bookUpdateMapper.conditionalUpdateStatus(book),
                historyUpdateMapper.conditionalUpdateHistory(buildHistoryWithHistoryId(book, status))
        );
    }

}
