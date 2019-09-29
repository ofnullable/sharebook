package org.slam.publicshare.rental.utils;

import org.slam.publicshare.rental.domain.Rental;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalUtils {

    private static Rental rental = Rental.builder()
                .accountId(1L)
                .bookId(1L)
                .build();

    public static Rental buildRequestedRental() {
        var rental = Rental.builder()
                .accountId(1L)
                .bookId(1L)
                .build();
        rental.rental();
        return rental;
    }

    public static Rental buildAcceptedRental() {
        var rental = Rental.builder()
                .accountId(1L)
                .bookId(1L)
                .build();
        rental.rental();
        rental.accept();
        return rental;
    }

    public static List<Rental> buildRentalList() {
        return List.of(rental, rental, rental);
    }

    public static void equalRental(Rental result, Rental target) {
        assertEquals(result.getBookId(), target.getBookId());
        assertEquals(result.getAccountId(), target.getAccountId());
        assertEquals(result.getHistories(), target.getHistories());
    }

}
