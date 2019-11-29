package me.ofnullable.sharebook.review.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.dto.UpdateReviewRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewUpdateService {

    private final ReviewFindService reviewFindService;

    @Transactional
    public Review updateReview(UpdateReviewRequest dto) {
        var review = reviewFindService.findByReviewId(dto.getId());
        return review.update(dto);
    }

}
