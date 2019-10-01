package org.slam.publicshare.rental.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.rental.domain.Rental;
import org.slam.publicshare.rental.exception.NoSuchRentalException;
import org.slam.publicshare.rental.repository.RentalRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.rental.utils.RentalUtils.*;

@ExtendWith(SpringExtension.class)
public class RentalFindServiceTest {

    @InjectMocks
    private RentalFindService rentalFindService;

    @Mock
    private RentalRepository rentalRepository;

    private Rental requested = buildRequestedRental();

    @Test
    @DisplayName("대여 Id로 대여기록 조회")
    public void find_by_id() {
        given(rentalRepository.findById(any(Long.class)))
                .willReturn(Optional.of(requested));

        var result = rentalFindService.findById(1L);

        equalRental(result, requested);
    }

    @Test
    @DisplayName("대여기록 존재하지 않는 경우 - NoSuchRentalException")
    public void find_by_invalid_id() {
        given(rentalRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        assertThrows(NoSuchRentalException.class, () -> rentalFindService.findById(1L));
    }

    @Test
    @DisplayName("특정 계정의 대여기록 조회")
    public void find_by_account_id() {
        given(rentalRepository.findAllByAccountId(any(Long.class)))
                .willReturn(buildRentalList());

        var result = rentalFindService.findAllByAccountId(1L);

        assertEquals(result.size(), 3);
    }

    @Test
    @DisplayName("존재하지 않는 계정의 대여기록 조회시 결과 0개")
    public void find_by_invalid_account_id() {
        given(rentalRepository.findAllByAccountId(any(Long.class)))
                .willReturn(new ArrayList<>());

        var result = rentalFindService.findAllByAccountId(1L);

        assertEquals(result.size(), 0);
    }

    @Test
    @DisplayName("특정 도서의 대여 기록 조회")
    public void find_by_book_id() {
        given(rentalRepository.findAllByBookIdOrderByIdDesc(any(Long.class)))
                .willReturn(buildRentalList());

        var result = rentalFindService.findAllByBookId(1L);

        assertEquals(result.size(), 3);
    }

    @Test
    @DisplayName("존재하지 않는 도서의 대여 기록 조회시 결과 0개")
    public void find_by_invalid_book_id() {
        given(rentalRepository.findAllByBookIdOrderByIdDesc(any(Long.class)))
                .willReturn(new ArrayList<>());

        var result = rentalFindService.findAllByBookId(1L);

        assertEquals(result.size(), 0);
    }

}
