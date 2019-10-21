package org.slam.publicshare.lending.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.account.exception.NoSuchAccountException;
import org.slam.publicshare.account.service.AccountFindService;
import org.slam.publicshare.book.domain.Book;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.book.utils.BookUtils.buildBook;
import static org.slam.publicshare.lending.utils.LendingUtils.buildLending;
import static org.slam.publicshare.lending.utils.LendingUtils.buildRequestedLending;

@ExtendWith(SpringExtension.class)
public class LendingValidatorTest {

    @InjectMocks
    private LendingValidator validator;

    @Mock
    private AccountFindService accountFindService;


    @Test
    @DisplayName("존재하지 않는 계정 Id로 대여 요청 시 - NoSuchAccountException")
    public void borrow_with_invalid_account_id() {
        given(accountFindService.findById(any(Long.class)))
                .willThrow(NoSuchAccountException.class);

        var lending = buildRequestedLending();

        assertThrows(NoSuchAccountException.class, () -> validator.validate(lending));
    }

    @Test
    @DisplayName("대여 불가능한 도서 대여 시 - IllegalStateException")
    public void borrow_unavailable_status_book() {
        var book = buildBook();
        book.toUnavailable(1L);
        var lending = buildLending(book);

        assertThrows(IllegalStateException.class, () -> validator.validate(lending));
    }

    @Test
    @DisplayName("자신의 도서 대여 시 - IllegalArgumentException")
    public void borrow_my_book() throws NoSuchFieldException, IllegalAccessException {
        var book = buildBookWithOwner(buildBook());
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
