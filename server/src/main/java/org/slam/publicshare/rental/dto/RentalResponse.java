package org.slam.publicshare.rental.dto;

import lombok.Getter;
import org.slam.publicshare.book.dto.book.BookResponse;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalHistory;
import org.slam.publicshare.rental.domain.RentalStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RentalResponse {

    private Long id;
    private BookResponse book;
    private RentalStatus currentStatus;
//    private List<RentalHistory> histories;

    public RentalResponse(Rental rental) {
        this.id = rental.getId();
        this.book = new BookResponse(rental.getBook());
        this.currentStatus = rental.getCurrentStatus();
//        this.histories = getHistories(rental);
    }

    private List<RentalHistory> getHistories(Rental rental) {
        return rental.getHistories().size() > 0 ? rental.getHistories() : new ArrayList<>();
    }

}
