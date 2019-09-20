package org.slam.publicshare.rental.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.repository.RentalRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalSaveService {

    private final RentalRepository rentalRepository;

    public Rental rentalRequest(Long bookId, Long accountId) {
        var rental = Rental.builder().bookId(bookId).accountId(accountId).build();
        rental.rental();
        return rentalRepository.save(rental);
    }

}
