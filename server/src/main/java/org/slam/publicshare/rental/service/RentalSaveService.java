package org.slam.publicshare.rental.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.slam.publicshare.rental.repository.RentalRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalSaveService {

    private final RentalRepository rentalRepository;

    public Rental save(Long bookId, Long accountId) {
        var rental = Rental.builder().bookId(bookId).accountId(accountId).build();
        rental.addHistory(RentalStatus.REQUESTED);
        return rentalRepository.save(rental);
    }

}
