package org.slam.mapper.book;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookStatus;

import java.util.List;

public interface HistoryMapper {

    @Insert("INSERT INTO BOOK_HISTORY(BOOK_ID, REQUESTED_STATUS, REQUESTED_USER) VALUES(#{id}, #{status}, #{modifiedBy})")
    int insertHistory(Book book);

    @Select("SELECT * FROM BOOK_HISTORY h JOIN BOOK b ON h.BOOK_ID = b.id WHERE REQUESTED_USER = #{username} AND REQUESTED_STATUS = #{status}")
    List<Book> selectMatchStatusHistory(@Param("status") BookStatus status, @Param("username") String username);
}
