package me.ofnullable.sharebook.review.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.book.service.BookFindService;
import me.ofnullable.sharebook.lending.service.LendingFindService;
import me.ofnullable.sharebook.review.domain.Review;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewValidator {

    private final BookFindService bookFindService;
    private final AccountFindService accountFindService;
    private final LendingFindService lendingFindService;

    void validateAccountAndBook(Review review) {
        isValidAccountId(review.getReviewerId());
        isValidBookId(review.getBookId());
        lentHistoryExists(review.getReviewerId(), review.getBookId());
    }

    private void isValidAccountId(Long reviewerId) {
        accountFindService.findById(reviewerId);
    }

    private void isValidBookId(Long bookId) {
        bookFindService.findById(bookId);
    }

    private void lentHistoryExists(Long reviewerId, Long bookId) {
        lendingFindService.findByBorrowerIdAndBookId(reviewerId, bookId);
    }

}
