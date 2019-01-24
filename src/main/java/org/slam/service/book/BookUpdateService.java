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

    public int reservationRequest(Long id, String modifier) {
        return historyMapper.insertHistory(Book.builder().id(id).status(BookStatus.ON_RESERVED).modifiedBy(modifier).build());
    }

    public int cancelLoanRequest(Long id, String modifier) {
        var book = Book.builder().id(id).status(BookStatus.WAIT_FOR_RESPONSE).modifiedBy(modifier).build();
        var changeResult = bookUpdateMapper.updateStatus(book);
        book.setStatus(BookStatus.CANCELED);
        var insertResult = historyMapper.updateBookHistoryToCanceled(book);
        return changeResult > 0 ? insertResult > 0 ? 1 : 0 : 0;
    }

    public int returnRequest(Long id, String modifier) {
        return historyMapper.updateBookHistoryToReturnRequest(Book.builder().id(id).status(BookStatus.RETURN_REQUEST).modifiedBy(modifier).build());
    }

}
