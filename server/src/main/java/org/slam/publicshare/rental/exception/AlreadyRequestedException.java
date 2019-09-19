package org.slam.publicshare.rental.exception;

import lombok.Getter;

@Getter
public class AlreadyRequestedException extends RuntimeException {
    private Long rentalId;
    public AlreadyRequestedException(Long rentalId) {
        this.rentalId = rentalId;
    }
}
