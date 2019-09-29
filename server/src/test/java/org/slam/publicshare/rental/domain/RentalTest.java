package org.slam.publicshare.rental.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slam.publicshare.rental.exception.HistoryStatusEqualsException;
import org.slam.publicshare.rental.exception.RentalAlreadyCompletionException;
import org.slam.publicshare.rental.exception.RentalAlreadyStartedException;
import org.slam.publicshare.rental.exception.RentalNotRequestedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.slam.publicshare.rental.utils.RentalUtils.buildAcceptedRental;
import static org.slam.publicshare.rental.utils.RentalUtils.buildRequestedRental;

public class RentalTest {

    @Test
    @DisplayName("대여요청")
    public void save_rental_history_test() {
        var rental = buildRequestedRental();

        assertEquals(rental.getHistories().size(), 1);
        assertEquals(rental.getHistories().get(0).getStatus(), RentalStatus.REQUESTED);
    }

    @Test
    @DisplayName("대여수락")
    public void rental_accept_test() {
        var rental = buildAcceptedRental();

        assertEquals(rental.getHistories().size(), 2);
        assertEquals(rental.getHistories().get(1).getStatus(), RentalStatus.ACCEPTED);
    }

    @Test
    @DisplayName("대여거절")
    public void rental_reject_test() {
        var rental = buildRequestedRental();
        rental.reject();

        assertEquals(rental.getHistories().size(), 2);
        assertEquals(rental.getHistories().get(1).getStatus(), RentalStatus.REJECTED);
    }

    @Test
    @DisplayName("반납")
    public void rental_return_test() {
        var rental = buildAcceptedRental();
        rental.returned();

        assertEquals(rental.getHistories().size(), 3);
        assertEquals(rental.getHistories().get(2).getStatus(), RentalStatus.RETURNED);
    }

    @Test
    @DisplayName("존재하지 않는 요청에 대한 처리 시 - RentalNotRequestedException")
    public void invalid_first_status_test() {
        var rental = Rental.builder()
                .accountId(1L)
                .bookId(1L)
                .build();

        assertThrows(RentalNotRequestedException.class, rental::accept);
        assertThrows(RentalNotRequestedException.class, rental::reject);
        assertThrows(RentalNotRequestedException.class, rental::returned);
    }

    @Test
    @DisplayName("동일한 상태로 변경 시 - HistoryStatusEqualsException")
    public void same_status_test() {
        var rental = buildRequestedRental();

        assertThrows(HistoryStatusEqualsException.class, rental::rental);

        assertThrows(HistoryStatusEqualsException.class, () -> {
            rental.accept();
            rental.accept();
        });

        assertThrows(HistoryStatusEqualsException.class, () -> {
            rental.returned();
            rental.returned();
        });
    }

    @Test
    @DisplayName("이미 종료된 대여기록 수정시도 시 - RentalAlreadyCompletionException")
    public void already_completed_rental_test() {
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
    @DisplayName("이미 시작된 대여기록 거절 시 - RentalAlreadyStartedException")
    public void already_started_rental_test() {
        var accepted = buildAcceptedRental();

        assertThrows(RentalAlreadyStartedException.class, accepted::reject);
    }

}
