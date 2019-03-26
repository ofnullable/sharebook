package org.slam.mapper.history;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.slam.dto.book.Book;
import org.slam.dto.book.BookHistory;

public interface HistoryUpdateMapper {

    int updateBookHistoryToCanceled(Long id);

    void updateBookHistoryToOnLoan(BookHistory history);

    void updateBookHistoryStatus(BookHistory history);

    @Update("UPDATE BOOK_HISTORY SET REQUESTED_STATUS = 'WAIT_FOR_RESPONSE' " +
            "WHERE BOOK_ID = #{id} AND REQUESTED_STATUS = 'ON_RESERVED' ORDER BY REQUESTED_AT ASC LIMIT 1")
    void updateBookHistoryOnReservedToWaitForResponse(Long id);

    @Update("UPDATE BOOK_HISTORY SET REQUESTED_STATUS = #{status} WHERE BOOK_ID = #{id} AND REQUESTED_STATUS = 'ON_LOAN'")
    int updateBookHistoryToReturnRequest(Book book);

    @Update("UPDATE BOOK_HISTORY SET REQUESTED_STATUS = 'CANCELED', ENDED_AT = NOW() WHERE BOOK_ID = #{id} AND REQUESTED_USER = #{username}")
    int cancelReservation(@Param("id") Long id, @Param("username") String username);

}
