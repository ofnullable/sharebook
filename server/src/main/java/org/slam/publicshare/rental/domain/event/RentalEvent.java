package org.slam.publicshare.rental.domain.event;

import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalStatus;

public class RentalEvent {

    private Rental rental;

    public RentalEvent(Rental rental) {
        this.rental = rental;
    }

    public Long getBookId() {
        return this.rental.getBookId();
    }

    public Long getAccountId() {
        return this.rental.getAccountId();
    }

    public RentalStatus getLastStatus() {
        var histories = this.rental.getHistories();
        return histories.get(histories.size() - 1).getStatus();
    }

}
