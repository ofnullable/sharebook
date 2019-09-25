package org.slam.publicshare.book.repository;

import org.slam.publicshare.book.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByDisplayOrderByName(boolean display);

    Optional<Category> findByIdAndDisplayIsTrue(Long id);

}
