package org.slam.publicshare.rental.dto;

import lombok.Getter;
import org.slam.publicshare.book.dto.book.BookResponse;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalStatus;

@Getter
public class RentalResponse {

    private Long id;
    private BookResponse book;
    private RentalStatus currentStatus;

    public RentalResponse(Rental rental) {
        this.id = rental.getId();
        this.book = new BookResponse(rental.getBook());
        this.currentStatus = rental.getCurrentStatus();
    }

}
