package org.slam.service.bookhistory;

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

    // WAIT_FOR_RESPONSE, ON_RESERVED, RETURN_REQUEST, CANCELED
    public int updateToMatchRequest(Book book) {
        System.out.println(book);
        if (BookStatus.CANCELED == book.getStatus()) {
            return cancelRequest(book);
        } else {
            return requestProcessing(book);
        }
    }

    public int updateToMatchResponse(Book book) {
        if (
                (BookStatus.WAIT_FOR_RESPONSE == book.getStatus() && OwnerAnswer.ACCEPT == book.getOwnerAnswer())
                        || (BookStatus.RETURN_REQUEST == book.getStatus() && OwnerAnswer.REJECT == book.getOwnerAnswer())
        ) {
            // 대여요청 수락 || 반납요청 거절의 경우 ON_LOAN 으로 상태 업데이트
            book.setStatus(BookStatus.ON_LOAN);
            return isSuccess(
                    bookUpdateMapper.updateStatus(book),
                    historyUpdateMapper.updateHistory(buildHistory(book, BookStatus.ON_LOAN))
            );
        } else {
            // 대여요청 거절 || 반납요청 수락의 경우
            // `Book`의 상태는 AVAILABLE 또는 WAIT_FOR_RESPONSE,
            // 'BookHistory'의 상태는 각각 REJECTED, RETURNED 로 변경
            return isSuccess(
                    bookUpdateMapper.conditionalUpdateStatus(book),
                    historyUpdateMapper.conditionalUpdateHistory(setMatchHistory(book))
            );
        }
    }

    private BookHistory setMatchHistory(Book book) {
        BookHistory result = null;
        if (BookStatus.WAIT_FOR_RESPONSE == book.getStatus() && OwnerAnswer.REJECT == book.getOwnerAnswer()) {
            result = buildHistory(book, BookStatus.REJECTED);
        } else if (BookStatus.RETURN_REQUEST == book.getStatus() && OwnerAnswer.ACCEPT == book.getOwnerAnswer()) {
            result = buildHistory(book, BookStatus.RETURNED);
        }
        return result;
    }

    private int cancelRequest(Book book) {
        if (BookStatus.RETURN_REQUEST == book.getOriginStatus()) {
            // update book && history to ON_LOAN
            return isSuccess(
                    0,
                    0
            );
        } else if (BookStatus.ON_RESERVED == book.getOriginStatus()) {
            // update history only to CANCELED
            return 0;
        } else {
            // conditional update book && history to AVAILABLE or WAIT_FOR_RESPONSE and CANCELED
            return isSuccess();
        }
    }

    private int requestProcessing(Book book) {
        int result = 0;
        switch (book.getStatus()) {
            case WAIT_FOR_RESPONSE:
            case RETURN_REQUEST:
                // update book && bookHistory
                result = isSuccess(
                        0,
                        0
                );
                break;
            case ON_RESERVED:
                // update history only
                result = 0;
                break;
        }
        return result;
    }

}
