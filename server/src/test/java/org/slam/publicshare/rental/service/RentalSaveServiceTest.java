package org.slam.publicshare.rental.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.book.exception.NoSuchBookException;
import org.slam.publicshare.book.service.BookFindService;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.repository.RentalRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.rental.utils.RentalUtils.buildRequestedRental;
import static org.slam.publicshare.rental.utils.RentalUtils.equalRental;

@ExtendWith(SpringExtension.class)
public class RentalSaveServiceTest {

    @InjectMocks
    private RentalSaveService rentalSaveService;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private BookFindService bookFindService;

    @Mock
    private RentalValidator validator;

    @Test
    @DisplayName("대여요청")
    public void rental_request() {
        var rental = buildRequestedRental();

        given(bookFindService.findById(any(Long.class)))
                .willReturn(rental.getBook());
        given(rentalRepository.save(any(Rental.class)))
                .willReturn(rental);

        var result = rentalSaveService.rentalRequest(1L, 1L);

        equalRental(result, rental);
    }

    @Test
    @DisplayName("존재하지 않는 도서 대여 시 - NoSuchBookException")
    public void rental_invalid_book() {
        given(bookFindService.findById(any(Long.class)))
                .willThrow(NoSuchBookException.class);

        assertThrows(NoSuchBookException.class, () -> rentalSaveService.rentalRequest(1L, 1L));
    }

}
