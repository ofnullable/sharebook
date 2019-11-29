package me.ofnullable.sharebook.review.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

class ReviewDtoTest {

    @Test
    @DisplayName("UpdateReviewRequest의 내용과 점수가 모두 null 일 경우 - IllegalArgumentException")
    void contents_and_score_can_not_both_null() {
        var exception = catchThrowable(() -> UpdateReviewRequest.builder().id(1L).contents(null).score(null).build());

        then(exception)
                .isInstanceOf(IllegalArgumentException.class);
    }

}
