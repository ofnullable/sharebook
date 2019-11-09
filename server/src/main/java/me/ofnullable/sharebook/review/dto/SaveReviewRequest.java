package me.ofnullable.sharebook.review.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.review.domain.Review;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveReviewRequest {

    @NotNull
    private Long bookId;
    @NotBlank
    private String contents;
    @NotNull
    private Integer score;
    private Long reviewerId;

    public SaveReviewRequest(Long bookId, String contents, Integer score) {
        this.bookId = bookId;
        this.contents = contents;
        this.score = score;
    }

    public Review toEntity(Long reviewerId) {
        return Review.builder()
                .bookId(bookId)
                .reviewerId(reviewerId)
                .contents(contents)
                .score(score)
                .build();
    }

}
