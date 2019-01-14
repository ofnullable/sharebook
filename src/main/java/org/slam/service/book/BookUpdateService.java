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
        var book = new Book(id, BookStatus.WAIT_FOR_RESPONSE, modifier);
        return this.changeStatus(book) > 0 ? this.insertHistory(book) > 0 ? 1 : 0 : 0;
    }

    public int reservationRequest(Long id, String modifier) {
        return historyMapper.insertHistory(new Book(id, BookStatus.ON_RESERVED, modifier));
    }

    public int cancelLoanRequest(Long id, String modifier) {
        var book = new Book(id, BookStatus.WAIT_FOR_RESPONSE, modifier);
        var changeResult = this.changeStatus(book);
        book.setStatus(BookStatus.CANCELED);
        var insertResult = this.insertHistory(book);
        return changeResult > 0 ? insertResult > 0 ? 1 : 0 : 0;
    }

    private int changeStatus(Book book) {
        return bookUpdateMapper.updateStatus(book);
    }

    private int insertHistory(Book book) {
        return historyMapper.insertHistory(book);
    }

}
