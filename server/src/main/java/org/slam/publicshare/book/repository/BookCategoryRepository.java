package org.slam.publicshare.book.repository;

import org.slam.publicshare.book.domain.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

    List<BookCategory> findAllByDisplayOrderByName(boolean display);

    Optional<BookCategory> findByNameAndDisplayIsTrue(String name);

}
