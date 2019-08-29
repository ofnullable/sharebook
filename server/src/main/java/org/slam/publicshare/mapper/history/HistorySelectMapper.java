package org.slam.publicshare.mapper.history;

import org.apache.ibatis.annotations.Param;
import org.slam.publicshare.dto.book.Book;
import org.slam.publicshare.dto.book.BookHistory;
import org.slam.publicshare.dto.book.BookStatus;
import org.slam.publicshare.dto.common.Paginator;

import java.util.List;

public interface HistorySelectMapper {

    Integer findTotalCount(@Param("status") BookStatus status, @Param("paginator") Paginator paginator);

    List<Book> findMatchStatusHistory(@Param("status") BookStatus status, @Param("paginator") Paginator paginator);

    List<BookHistory> findHistoryByBookId(@Param("bookId") Long bookId, @Param("username") String username);

    Book findHistoryDetailsByBookId(@Param("bookId") Long bookId, @Param("paginator") Paginator paginator);

    List<BookHistory> findBookRequestHistoryById(@Param("id") Long id, @Param("username") String username);

}
