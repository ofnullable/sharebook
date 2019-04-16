package org.slam.service.bookhistory;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookStatus;
import org.slam.dto.book.OwnerAnswer;
import org.slam.mapper.book.BookUpdateMapper;
import org.slam.mapper.history.HistorySaveMapper;
import org.slam.mapper.history.HistoryUpdateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.slam.utils.Builders.buildBook;
import static org.slam.utils.Builders.buildHistory;
import static org.slam.utils.TransactionUtils.isSuccess;

@Service
@Transactional
@AllArgsConstructor
public class BookHistoryService {

    private final BookUpdateMapper bookUpdateMapper;
    private final HistorySaveMapper historySaveMapper;
    private final HistoryUpdateMapper historyUpdateMapper;

    public int loanRequest(Long id, String modifier) {
        return isSuccess(
                bookUpdateMapper.updateStatus(buildBook(id, BookStatus.WAIT_FOR_RESPONSE, modifier)),
                historySaveMapper.insertHistory(buildHistory(id, BookStatus.WAIT_FOR_RESPONSE, modifier))
        );
    }

    public int cancelLoanRequest(Long id, String modifier) {
        return isSuccess(
                bookUpdateMapper.conditionalUpdateStatus(buildBook(id, null, modifier)),
                historyUpdateMapper.conditionalUpdateHistory(buildHistory(id, null, modifier))
        );
    }

    public int updateToMatchResponse(Book book) {
        // 대여요청 수락 || 반납요청 거절의 경우 ON_LOAN 으로 상태 업데이트
        var requestedStatus = book.getHistories().get(0).getRequestedStatus();
        if (
                (BookStatus.WAIT_FOR_RESPONSE == requestedStatus && OwnerAnswer.ACCEPT == book.getOwnerAnswer())
                        || (BookStatus.RETURN_REQUEST == requestedStatus && OwnerAnswer.REJECT == book.getOwnerAnswer())
        ) {
            return updateToOnLoan(book);
        } else {
            return updateMatchStatus(book);
        }
    }

    private int updateToOnLoan(Book book) {
        return isSuccess(
                bookUpdateMapper.updateStatus(book),
                historyUpdateMapper.updateHistory(buildHistory(book, BookStatus.ON_LOAN))
        );
    }

    private int updateMatchStatus(Book book) {
        // 대여요청 거절 || 반납요청 수락의 경우 `Book`의 상태는 AVAILABLE, 'BookHistory'의 상태는 각각 REJECTED, RETURNED 로 변경
        var requestedStatus = book.getHistories().get(0).getRequestedStatus();
        if (BookStatus.WAIT_FOR_RESPONSE == requestedStatus && OwnerAnswer.REJECT == book.getOwnerAnswer()) {
            return isSuccess(
                    bookUpdateMapper.conditionalUpdateStatus(book),
                    historyUpdateMapper.conditionalUpdateHistory(buildHistory(book, BookStatus.REJECTED))
            );
        } else if (BookStatus.RETURN_REQUEST == requestedStatus && OwnerAnswer.ACCEPT == book.getOwnerAnswer()) {
            return isSuccess(
                    bookUpdateMapper.conditionalUpdateStatus(book),
                    historyUpdateMapper.conditionalUpdateHistory(buildHistory(book, BookStatus.RETURNED))
            );
        } else if (BookStatus.CANCELED == requestedStatus) {
            return isSuccess(
                    bookUpdateMapper.conditionalUpdateStatus(book),
                    historyUpdateMapper.conditionalUpdateHistory(buildHistory(book, BookStatus.CANCELED))
            );
        }
        return 0;
    }

}
