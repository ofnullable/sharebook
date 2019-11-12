package me.ofnullable.sharebook.lending.repository;

import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LendingRepository extends JpaRepository<Lending, Long> {

    @EntityGraph(attributePaths = {"book", "book.category", "book.owner"})
    Page<Lending> findAllByBorrowerIdAndCurrentStatus(Long borrowerId, LendingStatus status, Pageable pageable);

    @EntityGraph(attributePaths = {"book", "book.category", "book.owner"})
    Page<Lending> findAllByBookOwnerIdAndCurrentStatus(Long owner, LendingStatus status, Pageable pageable);

    @EntityGraph(attributePaths = {"book", "book.category", "book.owner"})
    Optional<Lending> findFirstByBookIdOrderByIdDesc(Long bookId);

    Optional<Lending> findFirstByBorrowerIdAndBookId(Long borrowerId, Long bookId);

}
