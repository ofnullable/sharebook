package org.slam.mapper.book;

import org.apache.ibatis.annotations.Param;
import org.slam.dto.book.Book;

import java.util.List;

public interface BookSelectMapper {

    Book findById(@Param("id") Long id, @Param("username") String username);

    List<Book> findAll();

    List<Book> findAllByOwner(String owner);

}