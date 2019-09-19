package org.slam.publicshare.rental.exception;

import lombok.Getter;
import org.slam.publicshare.rental.domain.RentalStatus;

@Getter
public class HistoryStatusEqualsException extends RuntimeException {
    private RentalStatus status;
    public HistoryStatusEqualsException(RentalStatus state) {
        this.status = state;
    }
}
