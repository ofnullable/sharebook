package me.ofnullable.sharebook.lending.service;

import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.book.domain.Book;
import me.ofnullable.sharebook.book.utils.BookUtils;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static me.ofnullable.sharebook.lending.utils.LendingUtils.buildLending;
import static me.ofnullable.sharebook.lending.utils.LendingUtils.buildRequestedLending;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class LendingValidatorTest {

    @InjectMocks
    private LendingValidator validator;

    @Mock
    private AccountFindService accountFindService;

    @Test
    @DisplayName("존재하지 않는 계정 Id로 대여 요청 시 - ResourceNotFoundException")
    void borrow_with_invalid_account_id() {
        given(accountFindService.findById(any(Long.class)))
                .willThrow(ResourceNotFoundException.class);

        var lending = buildRequestedLending();

        assertThrows(ResourceNotFoundException.class, () -> validator.validate(lending));
    }

    @Test
    @DisplayName("대여 불가능한 도서 대여 시 - IllegalStateException")
    void borrow_unavailable_status_book() {
        var book = BookUtils.buildBook();
        book.toUnavailable(1L);
        var lending = buildLending(book);

        assertThrows(IllegalStateException.class, () -> validator.validate(lending));
    }

    @Test
    @DisplayName("자신의 도서 대여 시 - IllegalArgumentException")
    void borrow_my_book() throws NoSuchFieldException, IllegalAccessException {
        var book = buildBookWithOwner(BookUtils.buildBook());
        var lending = buildLending(book);

        assertThrows(IllegalArgumentException.class, () -> validator.validate(lending));
    }

    private Book buildBookWithOwner(Book book) throws NoSuchFieldException, IllegalAccessException {
        if (!book.isAvailable()) book.toAvailable();
        var account = book.getOwner();
        var accountId = account.getClass()
                .getDeclaredField("id");

        accountId.setAccessible(true);
        accountId.set(account, 1L);
        return book;
    }


}
