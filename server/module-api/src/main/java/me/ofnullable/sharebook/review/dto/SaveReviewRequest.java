package me.ofnullable.sharebook.review.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.common.domain.SimpleAccountInfo;
import me.ofnullable.sharebook.review.domain.Review;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveReviewRequest {

    @NotNull
    private Long bookId;
    @NotBlank
    private String contents;
    @Min(1)
    @Max(5)
    @NotNull
    private Integer score;

    public SaveReviewRequest(Long bookId, String contents, Integer score) {
        this.bookId = bookId;
        this.contents = contents;
        this.score = score;
    }

    public Review toEntity(Account reviewer) {
        return Review.builder()
                .bookId(bookId)
                .reviewer(SimpleAccountInfo.of(reviewer))
                .contents(contents)
                .score(score)
                .build();
    }

}
