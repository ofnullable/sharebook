package org.slam.publicshare.rental.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.slam.publicshare.rental.exception.NoSuchRentalException;
import org.slam.publicshare.rental.exception.RentalStatusEqualsException;
import org.slam.publicshare.rental.exception.RentalStatusInvalidException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.rental.utils.RentalUtils.buildAcceptedRental;
import static org.slam.publicshare.rental.utils.RentalUtils.buildRequestedRental;

@ExtendWith(SpringExtension.class)
public class RentalUpdateServiceTest {

    @InjectMocks
    private RentalUpdateService rentalUpdateService;

    @Mock
    private RentalFindService rentalFindService;

    @Test
    @DisplayName("ACCEPTED로 status 업데이트")
    public void update_rental_to_accepted_test() {
        var rental = buildRequestedRental();

        given(rentalFindService.findById(any(Long.class)))
                .willReturn(rental);

        var result = rentalUpdateService.updateRental(1L, RentalStatus.ACCEPTED);

        assertEquals(result.getHistories().size(), 2);
        assertEquals(result.getHistories().get(1).getStatus(), RentalStatus.ACCEPTED);
    }

    @Test
    @DisplayName("REJECTED로 status 업데이트")
    public void update_rental_to_rejected_test() {
        var rental = buildRequestedRental();

        given(rentalFindService.findById(any(Long.class)))
                .willReturn(rental);

        var result = rentalUpdateService.updateRental(1L, RentalStatus.REJECTED);

        assertEquals(result.getHistories().size(), 2);
        assertEquals(result.getHistories().get(1).getStatus(), RentalStatus.REJECTED);
    }

    @Test
    @DisplayName("RETURNED로 status 업데이트")
    public void update_rental_to_returned_test() {
        var rental = buildAcceptedRental();

        given(rentalFindService.findById(any(Long.class)))
                .willReturn(rental);

        var result = rentalUpdateService.updateRental(1L, RentalStatus.RETURNED);

        assertEquals(result.getHistories().size(), 3);
        assertEquals(result.getHistories().get(2).getStatus(), RentalStatus.RETURNED);
    }

    @Test
    @DisplayName("존재하지 않는 대여기록 업데이트 시 - NoSuchRentalException")
    public void update_not_requested_rental_test() {
        given(rentalFindService.findById(any(Long.class)))
                .willThrow(NoSuchRentalException.class);

        assertThrows(NoSuchRentalException.class, () -> rentalUpdateService.updateRental(1L, RentalStatus.ACCEPTED));
    }

    @Test
    @DisplayName("이전 상태와 같은 상태로 변경 요청 시 - RentalStatusEqualsException")
    public void update_rental_to_same_status_test() {
        var rental = buildAcceptedRental();

        given(rentalFindService.findById(any(Long.class)))
                .willReturn(rental);

        assertThrows(RentalStatusEqualsException.class, () -> rentalUpdateService.updateRental(1L, RentalStatus.ACCEPTED));

        rental.returned();
        assertThrows(RentalStatusEqualsException.class, () -> rentalUpdateService.updateRental(1L, RentalStatus.RETURNED));
    }

    @Test
    @DisplayName("유효하지 않은 상태로 변경 요청 시 - RentalStatusInvalidException")
    public void update_rental_requested_to_returned_test() {
        var rental = buildRequestedRental();

        given(rentalFindService.findById(any(Long.class)))
                .willReturn(rental);

        assertThrows(RentalStatusInvalidException.class, () -> rentalUpdateService.updateRental(1L, RentalStatus.RETURNED));

        rental.accept();
        assertThrows(RentalStatusInvalidException.class, () -> rentalUpdateService.updateRental(1L, RentalStatus.REJECTED));
    }

    @Test
    @DisplayName("REQUESTED로 상태 변경 시 - RentalStatusInvalidException")
    public void update_to_requested_test() {
        var rental = Rental.builder().accountId(1L).bookId(1L).build();

        given(rentalFindService.findById(any(Long.class)))
                .willReturn(rental);

        assertThrows(RentalStatusInvalidException.class, () -> rentalUpdateService.updateRental(1L, RentalStatus.REQUESTED));
    }

}
