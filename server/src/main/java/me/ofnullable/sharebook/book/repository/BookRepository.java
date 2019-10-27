package me.ofnullable.sharebook.book.repository;

import me.ofnullable.sharebook.book.domain.Book;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"category", "owner"})
    Page<Book> findAllByOrderByStatus(Pageable pageable);

    @EntityGraph(attributePaths = {"category", "owner"})
    Page<Book> findALlByTitleContainingIgnoreCaseOrderByStatus(String searchText, Pageable pageable);

    @EntityGraph(attributePaths = {"category", "owner"})
    Page<Book> findAllByCategoryNameOrderByStatus(String categoryName, Pageable pageable);

    @EntityGraph(attributePaths = {"category", "owner"})
    Page<Book> findAllByOwnerId(Long ownerId, Pageable pageable);

    @EntityGraph(attributePaths = {"category", "owner"})
    Page<Book> findAllByOwnerIdAndLendingsCurrentStatus(@Param("id") Long accountId, @Param("status") LendingStatus status, Pageable pageable);

}
