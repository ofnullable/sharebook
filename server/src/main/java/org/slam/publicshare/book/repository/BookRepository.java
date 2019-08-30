package org.slam.publicshare.book.repository;

import org.slam.publicshare.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
