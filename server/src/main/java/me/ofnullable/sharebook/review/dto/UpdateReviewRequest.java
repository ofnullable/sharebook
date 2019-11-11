package me.ofnullable.sharebook.review.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateReviewRequest {

    @NotNull
    private Long id;
    @Length(max = 255)
    private String contents;
    private Integer score;

    @Builder
    public UpdateReviewRequest(Long id, String contents, Integer score) {
        this.id = id;
        if (contents == null && score == null) {
            throw new IllegalArgumentException();
        }
        if (contents != null) {
            this.contents = contents;
        }
        if (score != null) {
            this.score = score;
        }
    }

}
