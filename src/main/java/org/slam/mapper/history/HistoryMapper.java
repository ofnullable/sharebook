package org.slam.mapper.history;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;
import org.slam.dto.common.Paginator;

import java.util.List;

public interface HistoryMapper {

    Integer findTotalCount(@Param("status") BookStatus status, @Param("paginator") Paginator paginator);

    @Insert("INSERT INTO BOOK_HISTORY(BOOK_ID, REQUESTED_STATUS, REQUESTED_USER) VALUES(#{id}, #{status}, #{modifiedBy})")
    int insertHistory(Book book);

    List<Book> findMatchStatusHistory(@Param("status") BookStatus status, @Param("paginator") Paginator paginator);

    List<BookHistory> findHistoryByBookId(@Param("bookId") Long bookId, @Param("username") String username);

    Book findHistoryDetailsByBookId(@Param("bookId") Long bookId, @Param("paginator") Paginator paginator);

    List<BookHistory> findBookRequestHistoryById(@Param("id") Long id, @Param("username") String username);

}
