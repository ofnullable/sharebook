package org.slam.mapper.history;

import org.slam.dto.book.BookHistory;

public interface HistoryUpdateMapper {

    // 전달받은 `status`로 변경할 때 사용
    int updateHistory(BookHistory history);

    // 예약자 유무에 따라 상황에 맞는 `status`로 update 해야하는 경우에 사용
    int conditionalUpdateHistory(BookHistory history);

    /*
    @Update("UPDATE BOOK_HISTORY SET REQUESTED_STATUS = #{status}, MODIFIED_BY = #{modifiedBy} WHERE BOOK_ID = #{id} AND REQUESTED_STATUS = 'ON_LOAN'")
    int updateBookHistoryToReturnRequest(Book book);

    @Update("UPDATE BOOK_HISTORY SET REQUESTED_STATUS = 'CANCELED', MODIFIED_BY = #{username}, ENDED_AT = NOW() WHERE BOOK_ID = #{id} AND REQUESTED_USER = #{username}")
    int cancelReservation(@Param("id") Long id, @Param("username") String username);

    @Update("UPDATE BOOK_HISTORY SET REQUESTED_STATUS = 'WAIT_FOR_RESPONSE' " +
            "WHERE BOOK_ID = #{id} AND REQUESTED_STATUS = 'ON_RESERVED' ORDER BY REQUESTED_AT ASC LIMIT 1")
    void updateBookHistoryOnReservedToWaitForResponse(Long id);

    void updateBookHistoryToOnLoan(BookHistory history);

    void updateBookHistoryStatus(BookHistory history);
    */

}
