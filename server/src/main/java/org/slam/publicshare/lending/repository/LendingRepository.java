package org.slam.publicshare.lending.repository;

import org.slam.publicshare.lending.domain.Lending;
import org.slam.publicshare.lending.domain.LendingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LendingRepository extends JpaRepository<Lending, Long> {

    @EntityGraph(attributePaths = {"book", "book.category", "book.owner"})
    Page<Lending> findAllByAccountIdAndCurrentStatus(Long accountId, LendingStatus lendingStatus, Pageable pageable);

}
