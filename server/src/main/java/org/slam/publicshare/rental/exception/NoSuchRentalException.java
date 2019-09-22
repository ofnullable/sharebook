package org.slam.publicshare.rental.exception;

import lombok.Getter;

@Getter
public class NoSuchRentalException extends RuntimeException {
    private Long rentalId;
    public NoSuchRentalException(Long rentalId) {
        this.rentalId = rentalId;
    }
}
