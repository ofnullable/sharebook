package org.slam.publicshare.rental.repository;

import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @EntityGraph(attributePaths = {"book", "book.category", "book.owner", "histories"})
    Page<Rental> findAllByAccountIdAndCurrentStatus(Long accountId, RentalStatus rentalStatus, Pageable pageable);

}
