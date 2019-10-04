package org.slam.publicshare.rental.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slam.publicshare.rental.exception.RentalAlreadyCompletionException;
import org.slam.publicshare.rental.exception.RentalNotRequestedException;
import org.slam.publicshare.rental.exception.RentalStatusEqualsException;
import org.slam.publicshare.rental.exception.RentalStatusInvalidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.slam.publicshare.rental.utils.RentalUtils.*;

public class RentalTest {

    @Test
    @DisplayName("대여요청")
    public void save_rental_history() {
        var rental = buildRequestedRental();

        assertEquals(rental.getHistories().size(), 1);
        assertEquals(rental.getHistories().get(0).getStatus(), RentalStatus.REQUESTED);
    }

    @Test
    @DisplayName("대여수락")
    public void rental_accept() {
        var rental = buildAcceptedRental();

        assertEquals(rental.getHistories().size(), 2);
        assertEquals(rental.getHistories().get(1).getStatus(), RentalStatus.ACCEPTED);
    }

    @Test
    @DisplayName("대여거절")
    public void rental_reject() {
        var rental = buildRequestedRental();
        rental.reject();

        assertEquals(rental.getHistories().size(), 2);
        assertEquals(rental.getHistories().get(1).getStatus(), RentalStatus.REJECTED);
    }

    @Test
    @DisplayName("반납")
    public void rental_return() {
        var rental = buildAcceptedRental();
        rental.returned();

        assertEquals(rental.getHistories().size(), 3);
        assertEquals(rental.getHistories().get(2).getStatus(), RentalStatus.RETURNED);
    }

    @Test
    @DisplayName("존재하지 않는 요청에 대한 처리 시 - RentalNotRequestedException")
    public void invalid_first_status() {
        var rental = buildRental(1L);

        assertThrows(RentalNotRequestedException.class, rental::accept);
        assertThrows(RentalNotRequestedException.class, rental::reject);
        assertThrows(RentalNotRequestedException.class, rental::returned);
    }

    @Test
    @DisplayName("동일한 상태로 변경 시 - RentalStatusEqualsException")
    public void same_rental_status() {
        var rental = buildRequestedRental();

        assertThrows(RentalStatusEqualsException.class, rental::rental);

        assertThrows(RentalStatusEqualsException.class, () -> {
            rental.accept();
            rental.accept();
        });

        assertThrows(RentalStatusEqualsException.class, () -> {
            rental.returned();
            rental.returned();
        });
    }

    @Test
    @DisplayName("이미 종료된 대여기록 수정시도 시 - RentalAlreadyCompletionException")
    public void already_completed_rental() {
        var rejected = buildRequestedRental();
        rejected.reject();

        assertThrows(RentalAlreadyCompletionException.class, rejected::accept);
        assertThrows(RentalAlreadyCompletionException.class, rejected::returned);

        var returned = buildAcceptedRental();
        returned.returned();

        assertThrows(RentalAlreadyCompletionException.class, returned::accept);
        assertThrows(RentalAlreadyCompletionException.class, returned::reject);
    }

    @Test
    @DisplayName("이미 시작된 대여기록 거절 시 - RentalStatusInvalidException")
    public void already_started_rental() {
        var accepted = buildAcceptedRental();

        assertThrows(RentalStatusInvalidException.class, accepted::reject);
    }

    @Test
    @DisplayName("시작되지 않은 대여기록 반납요청 시 - RentalStatusInvalidException")
    public void change_not_started_rental_to_return() {
        var accepted = buildAcceptedRental();

        assertThrows(RentalStatusInvalidException.class, accepted::reject);
    }

}
