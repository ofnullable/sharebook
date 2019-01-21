package org.slam.mapper.book;

import org.slam.dto.book.Book;

import java.util.List;

public interface BookSelectMapper {

    Book findById(Long id);

    List<Book> findAll();

    List<Book> findAllByOwner(String owner);

}