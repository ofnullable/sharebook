package org.slam.publicshare.rental.exception;

import lombok.Getter;

@Getter
public class RentalNotRequestedException extends RuntimeException {
    private Long rentalId;
    public RentalNotRequestedException(Long rentalId) {
        this.rentalId = rentalId;
    }
}
