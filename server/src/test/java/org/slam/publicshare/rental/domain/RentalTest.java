package org.slam.publicshare.rental.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slam.publicshare.rental.utils.RentalUtils.buildRental;

public class RentalHistoryTest {

    @Test
    @DisplayName("대여요청")
    public void save_rental_history_test() {
        var rental = buildRental();
        rental.rental();

        assertEquals(rental.getHistories().size(), 1);
        assertEquals(rental.getHistories().get(0).getStatus(), RentalStatus.REQUESTED);
    }

    @Test
    @DisplayName("대여수락")
    public void rental_accept_test() {
        var rental = buildRental();
        rental.rental();
        rental.accept();

        assertEquals(rental.getHistories().size(), 2);
        assertEquals(rental.getHistories().get(1).getStatus(), RentalStatus.ACCEPTED);
    }

    @Test
    @DisplayName("대여거절")
    public void rental_reject_test() {
        var rental = buildRental();
        rental.rental();
        rental.reject();

        assertEquals(rental.getHistories().size(), 2);
        assertEquals(rental.getHistories().get(1).getStatus(), RentalStatus.REJECTED);
    }

    @Test
    @DisplayName("반납")
    public void rental_return_test() {
        var rental = buildRental();
        rental.rental();
        rental.accept();
        rental.returned();

        assertEquals(rental.getHistories().size(), 3);
        assertEquals(rental.getHistories().get(2).getStatus(), RentalStatus.RETURNED);
    }

}
