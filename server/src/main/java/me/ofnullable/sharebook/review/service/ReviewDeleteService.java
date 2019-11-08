package me.ofnullable.sharebook.review.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewDeleteService {

    private final ReviewFindService reviewFindService;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long delete(Long reviewId) {
        var review = reviewFindService.findByReviewId(reviewId);
        reviewRepository.delete(review);
        return reviewId;
    }

}
