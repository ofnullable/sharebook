package org.slam.mapper.book;

import org.apache.ibatis.annotations.Param;
import org.slam.dto.book.Book;
import org.slam.dto.common.Paginator;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BookSelectMapper {

    Book findById(@Param("id") Long id, @Param("auth") Authentication auth);

    List<Book> findAll(Paginator paginator);

    List<Book> findAllByOwner(Paginator paginator);

    Integer findTotalCount(Paginator searchText);
}