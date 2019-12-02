package me.ofnullable.sharebook.review.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.book.service.BookFindService;
import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReviewValidator {

    private final BookFindService bookFindService;
    private final AccountFindService accountFindService;
    private final ReviewRepository reviewRepository;

    @Transactional
    void isValidRequest(Review review) {
        isValidAccountId(review.getReviewer().getAccountId());
        isValidBookId(review.getBookId());
        reviewExists(review.getReviewer().getAccountId(), review.getBookId());
    }

    private void isValidAccountId(Long reviewerId) {
        accountFindService.findById(reviewerId);
    }

    private void isValidBookId(Long bookId) {
        bookFindService.findById(bookId);
    }

    private void reviewExists(Long reviewerId, Long bookId) {
        if (reviewRepository.findByReviewerAccountIdAndBookId(reviewerId, bookId).isPresent()) {
            throw new IllegalArgumentException("이미 리뷰를 작성한 도서입니다.");
        }
    }

}
