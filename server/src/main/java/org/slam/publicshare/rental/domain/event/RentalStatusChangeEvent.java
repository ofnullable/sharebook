package org.slam.publicshare.rental.domain.event;

import lombok.Getter;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalHistory;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;

import java.util.List;

@Getter
public class RentalStatusChangeEvent extends ApplicationEvent {

    private Long bookId;
    private Long accountId;
    private RentalStatus lastStatus;

    public RentalStatusChangeEvent(Object source) {
        super(source);
        var rental = (Rental) source;
        this.bookId = rental.getBookId();
        this.accountId = rental.getAccountId();
        this.lastStatus = getLastStatus(rental.getHistories());
    }

    private RentalStatus getLastStatus(List<RentalHistory> histories) {
        Assert.isTrue(histories.size() > 0, "History size must bigger than 0");
        return histories.get(histories.size() - 1).getStatus();
    }

}
