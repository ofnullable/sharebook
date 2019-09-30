package org.slam.publicshare.rental.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.repository.RentalRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.rental.utils.RentalUtils.equalRental;

@ExtendWith(SpringExtension.class)
public class RentalSaveServiceTest {

    @InjectMocks
    private RentalSaveService rentalSaveService;

    @Mock
    private RentalRepository rentalRepository;

    private Rental rental = Rental.builder().accountId(1L).bookId(1L).build();

    @Test
    @DisplayName("대여기록 저장")
    public void save_rental_test() {
        given(rentalRepository.save(any(Rental.class)))
                .willReturn(rental);

        var result = rentalSaveService.rentalRequest(1L, 1L);

        equalRental(result, rental);
    }

}
