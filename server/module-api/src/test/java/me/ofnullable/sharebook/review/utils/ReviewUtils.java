package me.ofnullable.sharebook.review.utils;

import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.dto.MyReviewResponse;
import me.ofnullable.sharebook.review.dto.SaveReviewRequest;
import me.ofnullable.sharebook.review.dto.UpdateReviewRequest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static me.ofnullable.sharebook.account.utils.AccountUtils.buildAccountWithId;
import static me.ofnullable.sharebook.utils.PageRequestUtils.buildPage;

public class ReviewUtils {

    public static SaveReviewRequest buildSaveRequest() {
        return new SaveReviewRequest(1L, "test contents", 5);
    }

    public static Review buildReview() {
        return buildSaveRequest().toEntity(buildAccountWithId());
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

    private static MyReviewResponse buildMyReviewResponse() {
        return new MyReviewResponse() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public Long getBookId() {
                return 65L;
            }

            @Override
            public String getBookTitle() {
                return "book title";
            }

            @Override
            public String getBookAuthor() {
                return "book author";
            }

            @Override
            public Long getAccountId() {
                return 2L;
            }

            @Override
            public String getName() {
                return "test2 user";
            }

            @Override
            public String getAvatar() {
                return null;
            }

            @Override
            public String getContents() {
                return "review contents";
            }

            @Override
            public Integer getScore() {
                return 5;
            }

            @Override
            public LocalDateTime getModifiedAt() {
                return LocalDateTime.now();
            }

            @Override
            public String getModifiedBy() {
                return "test2@asd.com";
            }

            @Override
            public LocalDateTime getCreatedAt() {
                return LocalDateTime.now();
            }

            @Override
            public String getCreatedBy() {
                return "test2@asd.com";
            }
        };
    }

    public static Page<MyReviewResponse> buildMyReviewResponsePage() {
        return buildPage(List.of(buildMyReviewResponse(), buildMyReviewResponse(), buildMyReviewResponse()), 10);
    }

}
