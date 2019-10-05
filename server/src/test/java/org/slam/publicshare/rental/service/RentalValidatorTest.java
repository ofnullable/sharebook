package org.slam.publicshare.rental.service;

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
import static org.slam.publicshare.rental.utils.RentalUtils.buildRental;
import static org.slam.publicshare.rental.utils.RentalUtils.buildRequestedRental;

@ExtendWith(SpringExtension.class)
public class RentalValidatorTest {

    @InjectMocks
    private RentalValidator validator;

    @Mock
    private AccountFindService accountFindService;


    @Test
    @DisplayName("존재하지 않는 계정 Id로 대여 요청 시 - NoSuchAccountException")
    public void rental_with_invalid_account_id() {
        given(accountFindService.findById(any(Long.class)))
                .willThrow(NoSuchAccountException.class);

        var rental = buildRequestedRental();

        assertThrows(NoSuchAccountException.class, () -> validator.validate(rental));
    }

    @Test
    @DisplayName("대여 불가능한 도서 대여 시 - IllegalStateException")
    public void rental_unavailable_status_book() {
        var book = buildBook();
        book.toUnavailable(1L);
        var rental = buildRental(book);

        assertThrows(IllegalStateException.class, () -> validator.validate(rental));
    }

    @Test
    @DisplayName("자신의 도서 대여 시 - IllegalArgumentException")
    public void rental_my_book() throws NoSuchFieldException, IllegalAccessException {
        var book = buildBookWithOwner(buildBook());
        var rental = buildRental(book);

        assertThrows(IllegalArgumentException.class, () -> validator.validate(rental));
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
