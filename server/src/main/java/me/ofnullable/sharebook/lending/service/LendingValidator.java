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
        isValidAccountId(lending.getBorrowerId());
        isAvailable(lending.getBook());
        isNotOwner(lending);
    }

    private void isValidAccountId(Long accountId) {
        // if account dose not exist, throws ResourceNotFoundException
        accountFindService.findById(accountId);
    }

    private void isAvailable(Book book) {
        if (!book.isAvailable()) {
            throw new IllegalStateException("대여불가 상태의 도서입니다.");
        }
    }

    private void isNotOwner(Lending lending) {
        var ownerId = lending.getBook().getOwner().getId();
        if (lending.getBorrowerId().equals(ownerId)) {
            throw new IllegalArgumentException("자신의 도서를 대여할 수 없습니다.");
        }
    }

}
