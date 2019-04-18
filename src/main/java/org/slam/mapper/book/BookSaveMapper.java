package org.slam.mapper.book;

import org.slam.dto.book.Book;

import java.util.List;

public interface BookSaveMapper {

    Long save(Book book);

    void saveAll(List<Book> books);

}
