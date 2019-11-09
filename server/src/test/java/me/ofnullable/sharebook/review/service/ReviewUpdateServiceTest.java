package me.ofnullable.sharebook.review.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static me.ofnullable.sharebook.review.utils.ReviewUtils.*;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class ReviewUpdateServiceTest {

    @InjectMocks
    private ReviewUpdateService reviewUpdateService;

    @Mock
    private ReviewFindService reviewFindService;

    @Test
    @DisplayName("리뷰 내용, 점수 업데이트")
    void update_review() {
        given(reviewFindService.findByReviewId(any(Long.class)))
                .willReturn(buildReview());

        var dto = buildUpdateRequest();

        var updated = reviewUpdateService.updateReview(dto);

        then(updated.getContents())
                .isEqualTo(dto.getContents());
        then(updated.getScore())
                .isEqualTo(dto.getScore());
    }

    @Test
    @DisplayName("리뷰 내용만 업데이트")
    void update_review_contents_only() {
        var review = buildReview();
        given(reviewFindService.findByReviewId(any(Long.class)))
                .willReturn(review);

        var dto = buildUpdateRequestOnlyContents();

        var updated = reviewUpdateService.updateReview(dto);

        then(updated.getContents())
                .isEqualTo(dto.getContents());
        then(updated.getScore())
                .isEqualTo(review.getScore());
    }

    @Test
    @DisplayName("리뷰 점수만 업데이트")
    void update_review_score_only() {
        var review = buildReview();
        given(reviewFindService.findByReviewId(any(Long.class)))
                .willReturn(review);

        var dto = buildUpdateRequestOnlyScore();

        var updated = reviewUpdateService.updateReview(dto);

        then(updated.getContents())
                .isEqualTo(review.getContents());
        then(updated.getScore())
                .isEqualTo(dto.getScore());
    }

    @Test
    @DisplayName("존재하지 않는 리뷰 업데이트 시 NoSuchElementException")
    void update_invalid_review() {
        given(reviewFindService.findByReviewId(any(Long.class)))
                .willThrow(NoSuchElementException.class);

        var dto = buildUpdateRequest();

        var exception = catchThrowable(() -> reviewUpdateService.updateReview(dto));

        then(exception)
                .isInstanceOf(NoSuchElementException.class);
    }

}
