package me.ofnullable.sharebook.lending.service;

import me.ofnullable.sharebook.book.exception.NoSuchBookException;
import me.ofnullable.sharebook.book.service.BookFindService;
import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.repository.LendingRepository;
import me.ofnullable.sharebook.lending.utils.LendingUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class LendingSaveServiceTest {

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
    void borrow_request() {
        var lending = LendingUtils.buildRequestedLending();

        given(bookFindService.findById(any(Long.class)))
                .willReturn(lending.getBook());
        given(lendingRepository.save(any(Lending.class)))
                .willReturn(lending);

        var result = lendingSaveService.borrowRequest(1L, 1L);

        LendingUtils.equalLending(result, lending);
    }

    @Test
    @DisplayName("존재하지 않는 도서 대여 시 - NoSuchBookException")
    void lending_invalid_book() {
        given(bookFindService.findById(any(Long.class)))
                .willThrow(NoSuchBookException.class);

        assertThrows(NoSuchBookException.class, () -> lendingSaveService.borrowRequest(1L, 1L));
    }

}
