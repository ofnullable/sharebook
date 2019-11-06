package me.ofnullable.sharebook.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.review.domain.Review;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewResponse {
    private Long id;
    private Long bookId;
    private Long reviewerId;
    private String contents;
    private Integer score;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private String createdBy;
    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.bookId = review.getBookId();
        this.reviewerId = review.getReviewerId();
        this.contents = review.getContents();
        this.score = review.getScore();
        this.modifiedAt = review.getModifiedAt();
        this.modifiedBy = review.getModifiedBy();
        this.createdAt = review.getCreatedAt();
        this.createdBy = review.getCreatedBy();
    }
}
