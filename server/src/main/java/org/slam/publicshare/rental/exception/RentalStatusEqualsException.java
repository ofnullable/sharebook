package org.slam.publicshare.rental.exception;

import lombok.Getter;
import org.slam.publicshare.rental.domain.RentalStatus;

@Getter
public class RentalStatusEqualsException extends RuntimeException {
    private RentalStatus status;
    public RentalStatusEqualsException(RentalStatus state) {
        this.status = state;
    }
}
