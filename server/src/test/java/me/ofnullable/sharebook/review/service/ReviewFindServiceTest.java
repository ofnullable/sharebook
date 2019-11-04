package me.ofnullable.sharebook.review.service;

import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static me.ofnullable.sharebook.review.utils.ReviewUtils.buildReview;
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
    void find_by_book_id() {
        given(reviewRepository.findAllByBookId(any(Long.class)))
                .willReturn(List.of(review));

        var list = reviewFindService.findAllByBookId(1L);

        then(list.size())
                .isEqualTo(1);
    }

}