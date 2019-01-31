package org.slam.mapper.book;

import org.slam.dto.book.Book;

public interface BookUpdateMapper {

    int updateStatus(Book book);

    int conditionalUpdateStatus(Book book);

}
