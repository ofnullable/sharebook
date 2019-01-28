package org.slam.mapper.book;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;
import org.slam.dto.book.BookStatus;

import java.util.List;

public interface HistoryMapper {

    @Insert("INSERT INTO BOOK_HISTORY(BOOK_ID, REQUESTED_STATUS, REQUESTED_USER) VALUES(#{id}, #{status}, #{modifiedBy})")
    int insertHistory(Book book);

    List<Book> selectMatchStatusHistory(@Param("status") BookStatus status, @Param("username") String username);

    List<BookHistory> selectHistoryById(@Param("id") Long id, @Param("username") String username);

    void updateBookHistoryToOnLoan(BookHistory history);

    void updateBookHistoryToAvailable(BookHistory history);

    @Update("UPDATE BOOK_HISTORY SET REQUESTED_STATUS = 'WAIT_FOR_RESPONSE' " +
            "WHERE BOOK_ID = #{id} AND REQUESTED_STATUS = 'ON_RESERVED' ORDER BY REQUESTED_AT ASC LIMIT 1")
    void updateBookHistoryOnReservedToWaitForResponse(Long id);

    int updateBookHistoryToCanceled(Long id);

    @Update("UPDATE BOOK_HISTORY SET REQUESTED_STATUS = #{status} WHERE BOOK_ID = #{id} AND REQUESTED_STATUS = 'ON_LOAN'")
    int updateBookHistoryToReturnRequest(Book book);

    @Update("UPDATE BOOK_HISTORY SET REQUESTED_STATUS = 'CANCELED', ENDED_AT = NOW() WHERE BOOK_ID = #{id} AND REQUESTED_USER = #{username}")
    int cancelReservation(@Param("id") Long id, @Param("username") String username);
}
