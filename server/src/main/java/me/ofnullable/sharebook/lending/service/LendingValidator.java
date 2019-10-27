package me.ofnullable.sharebook.lending.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.book.domain.Book;
import me.ofnullable.sharebook.lending.domain.Lending;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LendingValidator {

    private final AccountFindService accountFindService;

    void validate(Lending lending) {
        isValidAccountId(lending.getAccountId());
        isAvailable(lending.getBook());
        isNotOwner(lending);
    }

    private void isValidAccountId(Long accountId) {
        // if account dose not exist, throws NoSuchAccountException
        accountFindService.findById(accountId);
    }

    private void isAvailable(Book book) {
        if (!book.isAvailable()) {
            throw new IllegalStateException("Can not borrow unavailable status book");
        }
    }

    private void isNotOwner(Lending lending) {
        var ownerId = lending.getBook().getOwner().getId();
        if (lending.getAccountId().equals(ownerId)) {
            throw new IllegalArgumentException("Can not borrow own book");
        }
    }

}
