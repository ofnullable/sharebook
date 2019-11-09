package me.ofnullable.sharebook.review.service;

import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

import static me.ofnullable.sharebook.review.utils.ReviewUtils.buildReview;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class ReviewDeleteServiceTest {

    @InjectMocks
    private ReviewDeleteService reviewDeleteService;

    @Mock
    private ReviewFindService reviewFindService;

    @Mock
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("리뷰 삭제")
    void delete_review() {
        given(reviewFindService.findByReviewId(any(Long.class)))
                .willReturn(buildReview());

        then(reviewDeleteService.deleteReview(1L))
                .isEqualTo(1L);
    }

    @Test
    @DisplayName("존재하지 않는 리뷰 삭제 요청 시 NoSuchElementException")
    void delete_invalid_review() {
        given(reviewFindService.findByReviewId(any(Long.class)))
                .willThrow(NoSuchElementException.class);

        var exception = catchThrowable(() -> reviewDeleteService.deleteReview(1L));

        then(exception)
                .isInstanceOf(NoSuchElementException.class);
    }

}
