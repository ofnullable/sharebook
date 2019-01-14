package org.slam.mapper.book;

import org.apache.ibatis.annotations.Insert;
import org.slam.dto.book.Book;

public interface HistoryMapper {

    @Insert("INSERT INTO LOAN_HISTORY(BOOK_ID, STATUS, REQUESTED_USER) VALUES(#{id}, #{status}, #{modifiedBy})")
    int insertHistory(Book book);

}
