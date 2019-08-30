package org.slam.publicshare.mapper.book;

import org.slam.publicshare.dto.book.Book;

public interface BookUpdateMapper {

    // 전달받은 `status`로 변경하는 경우에 사용
    int updateStatus(Book book);

    // 예약자 유무에 따라 상황에 맞는 `status`로 update 해야하는 경우에 사용
    int conditionalUpdateStatus(Book book);

}
