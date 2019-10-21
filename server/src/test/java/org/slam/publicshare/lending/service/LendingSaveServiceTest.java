package org.slam.publicshare.lending.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.book.exception.NoSuchBookException;
import org.slam.publicshare.book.service.BookFindService;
import org.slam.publicshare.lending.domain.Lending;
import org.slam.publicshare.lending.repository.LendingRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.lending.utils.LendingUtils.buildRequestedLending;
import static org.slam.publicshare.lending.utils.LendingUtils.equalLending;

@ExtendWith(SpringExtension.class)
public class LendingSaveServiceTest {

    @InjectMocks
    private LendingSaveService lendingSaveService;

    @Mock
    private LendingRepository lendingRepository;

    @Mock
    private BookFindService bookFindService;

    @Mock
    private LendingValidator validator;

    @Test
    @DisplayName("대여요청")
    public void borrow_request() {
        var lending = buildRequestedLending();

        given(bookFindService.findById(any(Long.class)))
                .willReturn(lending.getBook());
        given(lendingRepository.save(any(Lending.class)))
                .willReturn(lending);

        var result = lendingSaveService.borrowRequest(1L, 1L);

        equalLending(result, lending);
    }

    @Test
    @DisplayName("존재하지 않는 도서 대여 시 - NoSuchBookException")
    public void lending_invalid_book() {
        given(bookFindService.findById(any(Long.class)))
                .willThrow(NoSuchBookException.class);

        assertThrows(NoSuchBookException.class, () -> lendingSaveService.borrowRequest(1L, 1L));
    }

}
