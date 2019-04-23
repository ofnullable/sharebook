package org.slam.mapper.history;

import org.slam.dto.book.BookHistory;

public interface HistoryUpdateMapper {

    // 전달받은 `status`로 변경할 때 사용
    int updateHistory(BookHistory history);

    // 예약자 유무에 따라 상황에 맞는 `status`로 update 해야하는 경우에 사용
    int conditionalUpdateHistory(BookHistory history);

}
