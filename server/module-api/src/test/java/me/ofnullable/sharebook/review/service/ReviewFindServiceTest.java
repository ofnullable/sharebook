package me.ofnullable.sharebook.review.service;

import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static me.ofnullable.sharebook.review.utils.ReviewUtils.buildMyReviewResponsePage;
import static me.ofnullable.sharebook.review.utils.ReviewUtils.buildReview;
import static me.ofnullable.sharebook.utils.PageRequestUtils.buildPageRequest;
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
        given(reviewRepository.findAllByBookIdOrderByIdDesc(any(Long.class)))
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
    @DisplayName("존재하지 않는 Id의 리뷰 조회 시 ResourceNotFoundException")
    void find_by_invalid_id() {
        given(reviewRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        var exception = catchThrowable(() -> reviewFindService.findByReviewId(1L));

        then(exception)
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    @DisplayName("리뷰작성자 Id로 리뷰 조회")
    void find_all_by_reviewer_id() {
        given(reviewRepository.findAllWithBookByReviewerId(any(Long.class), any(Pageable.class)))
                .willReturn(buildMyReviewResponsePage());

        var result = reviewFindService.findAllByReviewerId(1L, buildPageRequest(10));

        then(result.getSize())
                .isEqualTo(10);
        then(result.getTotalElements())
                .isEqualTo(3);
    }

    @Test
    @DisplayName("작성자 Id에 해당하는 Review가 존재하지 않는 경우")
    void find_all_by_invalid_reviewer_id() {
        given(reviewRepository.findAllWithBookByReviewerId(any(Long.class), any(Pageable.class)))
                .willReturn(Page.empty());

        var result = reviewFindService.findAllByReviewerId(1L, buildPageRequest(10));

        then(result.isEmpty())
                .isTrue();
    }

}