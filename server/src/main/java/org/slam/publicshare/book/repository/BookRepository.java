package org.slam.publicshare.book.repository;

import org.slam.publicshare.book.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = "category")
    Page<Book> findAll(Pageable pageable);

    Page<Book> findByTitleContainingIgnoreCase(String searchText, Pageable pageable);

}
