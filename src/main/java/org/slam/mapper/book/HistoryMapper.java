package org.slam.mapper.book;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookStatus;

import java.util.List;

public interface HistoryMapper {

    @Insert("INSERT INTO BOOK_HISTORY(BOOK_ID, REQUESTED_STATUS, REQUESTED_USER) VALUES(#{id}, #{status}, #{modifiedBy})")
    int insertHistory(Book book);

    List<Book> selectMatchStatusHistory(@Param("status") BookStatus status, @Param("username") String username);

}
