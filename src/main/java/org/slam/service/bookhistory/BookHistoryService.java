package org.slam.service.bookhistory;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookStatus;
import org.slam.mapper.book.BookUpdateMapper;
import org.slam.mapper.history.HistorySaveMapper;
import org.slam.mapper.history.HistoryUpdateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.slam.utils.TransactionUtils.isSuccess;

@Service
@Transactional
@AllArgsConstructor
public class BookHistoryService {

    private final BookUpdateMapper bookUpdateMapper;
    private final HistorySaveMapper historyMapper;
    private final HistoryUpdateMapper historyUpdateMapper;

    public int loanRequest(Long id, String modifier) {
        var book = Book.builder().id(id).status(BookStatus.WAIT_FOR_RESPONSE).modifiedBy(modifier).build();
        return isSuccess(bookUpdateMapper.updateStatus(book), historyMapper.insertHistory(book));
    }

    public int cancelLoanRequest(Long id, String modifier) {
        var book = Book.builder().id(id).modifiedBy(modifier).build();
        return isSuccess(bookUpdateMapper.conditionalUpdateStatus(book), historyUpdateMapper.updateBookHistoryToCanceled(book));
    }

    private int updateToON_LOAN(Book book) {
        return 0;
    }

    private int updateToAVAILABLE(Book book, BookStatus status) {
        return 0;
    }

}
