package org.slam.publicshare.rental.service;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.account.service.AccountFindService;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.rental.domain.Rental;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalValidator {

    private final AccountFindService accountFindService;

    void validate(Rental rental) {
        isValidAccountId(rental.getAccountId());
        isAvailable(rental.getBook());
        isNotOwner(rental);
    }

    private void isValidAccountId(Long accountId) {
        // if account dose not exist, throws NoSuchAccountException
        accountFindService.findById(accountId);
    }

    private void isAvailable(Book book) {
        if (!book.isAvailable()) {
            throw new IllegalStateException("Can not rental unavailable status book");
        }
    }

    private void isNotOwner(Rental rental) {
        var ownerId = rental.getBook().getOwner().getId();
        if (rental.getAccountId().equals(ownerId)) {
            throw new IllegalArgumentException("Can not rental own book");
        }
    }

}
