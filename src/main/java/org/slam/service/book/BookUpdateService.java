package org.slam.service.book;

import lombok.AllArgsConstructor;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookStatus;
import org.slam.mapper.book.BookUpdateMapper;
import org.slam.mapper.book.HistoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class BookUpdateService {

    private final BookUpdateMapper bookUpdateMapper;
    private final HistoryMapper historyMapper;

    public int loanRequest(Long id, String modifier) {
        var book = Book.builder().id(id).status(BookStatus.WAIT_FOR_RESPONSE).modifiedBy(modifier).build();
        return bookUpdateMapper.updateStatus(book) > 0 ? historyMapper.insertHistory(book) > 0 ? 1 : 0 : 0;
    }

    public int cancelLoanRequest(Long id, String modifier) {
        var book = Book.builder().id(id).modifiedBy(modifier).build();
        return bookUpdateMapper.conditionalUpdateStatus(book) > 0 ?
                historyMapper.updateBookHistoryToCanceled(book.getId()) > 0 ?
                        1 : 0 : 0;
    }

}
