package org.slam.publicshare.rental.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalUpdateService {

    private final RentalFindService rentalFindService;

    public Rental updateRental(Long rentalId, RentalStatus rentalStatus) {
        var rental = rentalFindService.findById(rentalId);
        switch (rentalStatus) {
            case ACCEPTED:
                rental.accept();
            case REJECTED:
                rental.reject();
            case RETURNED:
                rental.returned();
        }
        return rental;
    }

}
