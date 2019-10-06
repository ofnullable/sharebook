package org.slam.publicshare.rental.utils;

import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.rental.domain.Rental;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slam.publicshare.book.utils.BookUtils.buildBook;
import static org.slam.publicshare.book.utils.BookUtils.equalBook;

public class RentalUtils {

    private static Book book = buildBook();

    public static Rental buildRental(Long accountId) {
        return Rental.builder()
                .accountId(accountId)
                .book(book)
                .build();
    }

    public static Rental buildRental(Book book) {
        return Rental.builder()
                .accountId(1L)
                .book(book)
                .build();
    }

    public static Rental buildRequestedRental() {
        var rental = Rental.builder()
                .accountId(1L)
                .book(book)
                .build();
        rental.request();
        return rental;
    }

    public static Rental buildAcceptedRental() {
        var rental = Rental.builder()
                .accountId(1L)
                .book(book)
                .build();
        rental.request();
        rental.accept();
        return rental;
    }

    public static List<Rental> buildRentalList() {
        return List.of(buildRental(1L), buildRental(2L), buildRental(3L));
    }

    public static void equalRental(Rental result, Rental target) {
        equalBook(result.getBook(), target.getBook());
        assertEquals(result.getAccountId(), target.getAccountId());
        assertEquals(result.getHistories(), target.getHistories());
    }

}
