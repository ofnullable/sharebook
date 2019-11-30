package me.ofnullable.sharebook.review.service;

import me.ofnullable.sharebook.account.service.AccountFindService;
import me.ofnullable.sharebook.book.service.BookFindService;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static me.ofnullable.sharebook.review.utils.ReviewUtils.buildReview;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(SpringExtension.class)
class ReviewValidatorTest {

    @InjectMocks
    private ReviewValidator reviewValidator;

    @Mock
    private AccountFindService accountFindService;

    @Mock
    private BookFindService bookFindService;

    @Mock
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("isValidRequest 호출 시 각 메서드 동작")
    void valid_request() {
        reviewValidator.isValidRequest(buildReview());

        then(accountFindService)
                .should()
                .findById(any(Long.class));

        then(bookFindService)
                .should()
                .findById(any(Long.class));

        then(reviewRepository)
                .should()
                .findByReviewerAccountIdAndBookId(any(Long.class), any(Long.class));
    }

    @Test
    @DisplayName("존재하지 않는 계정 Id인 경우 ResourceNotFoundException")
    void invalid_account_id() {
        given(accountFindService.findById(any(Long.class)))
                .willThrow(ResourceNotFoundException.class);

        var exception = catchThrowable(() -> reviewValidator.isValidRequest(buildReview()));

        then(bookFindService)
                .shouldHaveNoInteractions();

        then(reviewRepository)
                .shouldHaveNoInteractions();

        BDDAssertions.then(exception)
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("존재하지 않는 도서 Id인 경우 ResourceNotFoundException")
    void invalid_book_id() {
        given(bookFindService.findById(any(Long.class)))
                .willThrow(ResourceNotFoundException.class);

        var exception = catchThrowable(() -> reviewValidator.isValidRequest(buildReview()));

        then(accountFindService)
                .should()
                .findById(any(Long.class));

        then(reviewRepository)
                .shouldHaveNoInteractions();

        BDDAssertions.then(exception)
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("리뷰가 이미 존재하는 경우 IllegalArgumentException")
    void review_already_exists() {
        var review = buildReview();
        given(reviewRepository.findByReviewerAccountIdAndBookId(any(Long.class), any(Long.class)))
                .willReturn(Optional.of(review));

        var exception = catchThrowable(() -> reviewValidator.isValidRequest(buildReview()));

        then(accountFindService)
                .should()
                .findById(any(Long.class));

        then(bookFindService)
                .should()
                .findById(any(Long.class));

        BDDAssertions.then(exception)
                .isInstanceOf(IllegalArgumentException.class);
    }

}
