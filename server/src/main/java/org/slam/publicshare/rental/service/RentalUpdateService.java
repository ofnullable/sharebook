package org.slam.publicshare.rental.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.slam.publicshare.rental.exception.RentalStatusInvalidException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RentalUpdateService {

    private final RentalFindService rentalFindService;

    @Transactional
    public Rental updateRental(Long id, RentalStatus rentalStatus) {
        var rental = rentalFindService.findById(id);
        switch (rentalStatus) {
            case ACCEPTED:
                rental.accept();
                break;
            case REJECTED:
                rental.reject();
                break;
            case RETURNED:
                rental.returned();
                break;
            default:
                throw new RentalStatusInvalidException(null, rentalStatus);
        }
        return rental;
    }

}
