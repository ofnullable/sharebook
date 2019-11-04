package me.ofnullable.sharebook.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.review.domain.Review;

@Getter
@NoArgsConstructor
public class ReviewResponse {
    private Long id;
    private Long bookId;
    private Long reviewerId;
    private String contents;
    private Integer score;
    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.bookId = review.getBookId();
        this.reviewerId = review.getReviewerId();
        this.contents = review.getContents();
        this.score = review.getScore();
    }
}
