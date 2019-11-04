package me.ofnullable.sharebook.review.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.book.service.BookFindService;
import me.ofnullable.sharebook.lending.service.LendingFindService;
import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.springframework.stereotype.Component;

import static me.ofnullable.sharebook.lending.domain.LendingStatus.RETURNED;

@Component
@RequiredArgsConstructor
public class ReviewValidator {

    private final BookFindService bookFindService;
    private final AccountFindService accountFindService;
    private final LendingFindService lendingFindService;
    private final ReviewRepository reviewRepository;

    void isValidRequest(Review review) {
        isValidAccountId(review.getReviewerId());
        isValidBookId(review.getBookId());
        lentHistoryExists(review.getReviewerId(), review.getBookId());
        reviewExists(review.getReviewerId(), review.getBookId());
    }

    private void isValidAccountId(Long reviewerId) {
        accountFindService.findById(reviewerId);
    }

    private void isValidBookId(Long bookId) {
        bookFindService.findById(bookId);
    }

    private void lentHistoryExists(Long reviewerId, Long bookId) {
        var lending = lendingFindService.findLatestByBorrowerIdAndBookId(reviewerId, bookId);
        if (lending.getCurrentStatus() != RETURNED) {
            throw new IllegalStateException("Only can review for returned book");
        }
    }

    private void reviewExists(Long reviewerId, Long bookId) {
        if (reviewRepository.findByReviewerIdAndBookId(reviewerId, bookId).isPresent()) {
            throw new IllegalArgumentException("Already reviewed this book");
        }
    }

}
