package me.ofnullable.sharebook.lending.repository;

import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendingRepository extends JpaRepository<Lending, Long> {

    @EntityGraph(attributePaths = {"book", "book.category", "book.owner"})
    Page<Lending> findAllByAccountIdAndCurrentStatus(Long accountId, LendingStatus lendingStatus, Pageable pageable);

}
