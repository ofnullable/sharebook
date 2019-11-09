package me.ofnullable.sharebook.review.utils;

import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.dto.SaveReviewRequest;
import me.ofnullable.sharebook.review.dto.UpdateReviewRequest;

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

    public static UpdateReviewRequest buildUpdateRequest() {
        return UpdateReviewRequest.builder()
                .id(1L)
                .contents("Review Update Test...")
                .score(4)
                .build();
    }

    public static UpdateReviewRequest buildUpdateRequestOnlyContents() {
        return UpdateReviewRequest.builder()
                .id(1L)
                .contents("Review Update Test Without Score...")
                .score(null)
                .build();
    }

    public static UpdateReviewRequest buildUpdateRequestOnlyScore() {
        return UpdateReviewRequest.builder()
                .id(1L)
                .contents(null)
                .score(1)
                .build();
    }

}
