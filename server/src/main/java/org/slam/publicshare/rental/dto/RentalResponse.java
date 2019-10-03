package org.slam.publicshare.rental.dto;

import lombok.Getter;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalHistory;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RentalResponse {

    private Long id;
    private Long bookId;
    private Long accountId;
    private List<RentalHistory> histories;

    public RentalResponse(Rental rental) {
        this.id = rental.getId();
        this.bookId = rental.getBook().getId();
        this.accountId = rental.getAccountId();
        this.histories = getHistories(rental);
    }

    private List<RentalHistory> getHistories(Rental rental) {
        return rental.getHistories().size() > 0 ? rental.getHistories() : new ArrayList<>();
    }

}
