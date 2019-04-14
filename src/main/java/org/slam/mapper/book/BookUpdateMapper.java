package org.slam.mapper.book;

import org.slam.dto.book.Book;

public interface BookUpdateMapper {

    // 전달받은 `book`의 상태로 변경하는 경우에 사용
    int updateStatus(Book book);

    // 예약자 유무에 따라 상황에 맞는 `status`로 update 돼야하는 경우에 사용
    int conditionalUpdateStatus(Book book);

}
