package me.ofnullable.sharebook.review.utils;

import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.dto.SaveReviewRequest;

import java.util.List;

public class ReviewUtils {

    public static SaveReviewRequest buildSaveRequest() {
        return new SaveReviewRequest(1L, "test contents", 5);
    }

    public static Review buildReview() {
        return buildSaveRequest().toEntity(2L);
    }

    public static List<Review> buildReviewList() {
        return List.of(buildReview(), buildReview(), buildReview());
    }
}
