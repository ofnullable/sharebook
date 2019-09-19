package org.slam.publicshare.rental.repository;

import org.slam.publicshare.rental.domain.Rental;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @EntityGraph(attributePaths = "histories")
    List<Rental> findAllByAccountId(Long accountId);

    @EntityGraph(attributePaths = "histories")
    List<Rental> findAllByBookIdOrderByIdDesc(Long bookId);

}
