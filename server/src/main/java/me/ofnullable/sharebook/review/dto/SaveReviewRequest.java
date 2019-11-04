package me.ofnullable.sharebook.review.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.review.domain.Review;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveReviewRequest {

    @NotNull
    private Long bookId;
    @NotNull
    private Long reviewerId;
    @NotBlank
    private String contents;
    @NotNull
    private Integer score;

    public SaveReviewRequest(Long bookId, Long reviewerId, String contents, Integer score) {
        this.bookId = bookId;
        this.reviewerId = reviewerId;
        this.contents = contents;
        this.score = score;
    }

    public Review toEntity() {
        return Review.builder()
                .bookId(bookId)
                .reviewerId(reviewerId)
                .contents(contents)
                .score(score)
                .build();
    }

}
