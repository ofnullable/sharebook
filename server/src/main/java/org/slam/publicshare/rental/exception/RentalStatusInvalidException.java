package org.slam.publicshare.rental.exception;

import lombok.Getter;
import org.slam.publicshare.rental.domain.RentalStatus;

@Getter
public class RentalStatusInvalidException extends RuntimeException {
    private RentalStatus oldStatus;
    private RentalStatus newStatus;
    public RentalStatusInvalidException(RentalStatus oldStatus, RentalStatus newStatus) {
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }
}
