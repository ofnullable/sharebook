package me.ofnullable.sharebook.review.service;

import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static me.ofnullable.sharebook.review.utils.ReviewUtils.buildReview;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class ReviewFindServiceTest {

    @InjectMocks
    private ReviewFindService reviewFindService;

    @Mock
    private ReviewRepository reviewRepository;

    private final Review review = buildReview();

    @Test
    @DisplayName("도서 Id와 연관된 리뷰 목록 조회")
    void find_by_book_id() {
        given(reviewRepository.findAllByBookId(any(Long.class)))
                .willReturn(List.of(review));

        var list = reviewFindService.findAllByBookId(1L);

        then(list.size())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("리뷰 Id로 조회")
    void find_by_review_id() {
        given(reviewRepository.findById(any(Long.class)))
                .willReturn(Optional.of(review));

        var result = reviewFindService.findByReviewId(1L);

        then(result.getId())
                .isEqualTo(review.getId());
        then(result.getBookId())
                .isEqualTo(review.getBookId());
        then(result.getReviewerId())
                .isEqualTo(review.getReviewerId());
        then(result.getScore())
                .isEqualTo(review.getScore());
        then(result.getContents())
                .isEqualTo(review.getContents());
    }

    @Test
    @DisplayName("존재하지 않는 Id의 리뷰 조회 시 NoSuchElementException")
    void find_by_invalid_id() {
        given(reviewRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        var exception = catchThrowable(() -> reviewFindService.findByReviewId(1L));

        then(exception)
                .isInstanceOf(NoSuchElementException.class);
    }

}