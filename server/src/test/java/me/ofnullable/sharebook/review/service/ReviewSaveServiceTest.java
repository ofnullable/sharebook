package me.ofnullable.sharebook.review.service;

import me.ofnullable.sharebook.account.exception.NoSuchAccountException;
import me.ofnullable.sharebook.book.exception.NoSuchBookException;
import me.ofnullable.sharebook.lending.exception.LendingHistoryNotExistsException;
import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.dto.SaveReviewRequest;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static me.ofnullable.sharebook.account.utils.AccountUtils.buildNormalAccount;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
class ReviewSaveServiceTest {

    @InjectMocks
    private ReviewSaveService reviewSaveService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewValidator validator;

    private final SaveReviewRequest request = new SaveReviewRequest(1L, "Test Review", 5);
    private final Review review = request.toEntity(1L);

    @Test
    @DisplayName("정상적인 review 등록")
    void save_valid_review() {
        given(reviewRepository.save(any(Review.class)))
                .willReturn(review);

        var result = reviewSaveService.save(request, buildNormalAccount());

        BDDMockito.then(validator)
                .should()
                .isValidRequest(any(Review.class));

        BDDMockito.then(reviewRepository)
                .should()
                .save(any());

        BDDAssertions.then(result)
                .isEqualTo(review);
    }

    @Test
    @DisplayName("존재하지 않는 도서에 대한 review 등록")
    void save_review_with_invalid_book_id() {
        doThrow(NoSuchBookException.class)
                .when(validator)
                .isValidRequest(any());

        var exception = catchThrowable(() -> reviewSaveService.save(request, buildNormalAccount()));

        BDDMockito.then(validator)
                .should()
                .isValidRequest(any(Review.class));

        BDDMockito.then(reviewRepository)
                .shouldHaveNoInteractions();

        BDDAssertions.then(exception)
                .isInstanceOf(NoSuchBookException.class);
    }

    @Test
    @DisplayName("존재하지 않는 계정으로 review 등록")
    void save_review_with_invalid_account_id() {
        doThrow(NoSuchAccountException.class)
                .when(validator)
                .isValidRequest(any(Review.class));

        var exception = catchThrowable(() -> reviewSaveService.save(request, buildNormalAccount()));

        BDDMockito.then(reviewRepository)
                .shouldHaveNoInteractions();

        BDDAssertions.then(exception)
                .isInstanceOf(NoSuchAccountException.class);
    }

    @Test
    @DisplayName("대여기록이 존재하지 않는 도서에 대한 review 등록")
    void save_review_with_no_lending_history() {
        doThrow(LendingHistoryNotExistsException.class)
                .when(validator)
                .isValidRequest(any(Review.class));

        var exception = catchThrowable(() -> reviewSaveService.save(request, buildNormalAccount()));

        BDDMockito.then(reviewRepository)
                .shouldHaveNoInteractions();

        BDDAssertions.then(exception)
                .isInstanceOf(LendingHistoryNotExistsException.class);
    }

}
