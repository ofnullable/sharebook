package me.ofnullable.sharebook.review.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.book.service.BookFindService;
import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewValidator {

    private final BookFindService bookFindService;
    private final AccountFindService accountFindService;
    private final ReviewRepository reviewRepository;

    void isValidRequest(Review review) {
        isValidAccountId(review.getReviewerId());
        isValidBookId(review.getBookId());
        reviewExists(review.getReviewerId(), review.getBookId());
    }

    private void isValidAccountId(Long reviewerId) {
        accountFindService.findById(reviewerId);
    }

    private void isValidBookId(Long bookId) {
        bookFindService.findById(bookId);
    }

    private void reviewExists(Long reviewerId, Long bookId) {
        if (reviewRepository.findByReviewerIdAndBookId(reviewerId, bookId).isPresent()) {
            throw new IllegalArgumentException("Already reviewed this book");
        }
    }

}
