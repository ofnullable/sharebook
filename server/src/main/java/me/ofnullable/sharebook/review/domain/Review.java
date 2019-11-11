package me.ofnullable.sharebook.review.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.common.domain.Auditable;
import me.ofnullable.sharebook.review.dto.UpdateReviewRequest;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private Long reviewerId;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Integer score;

    @Builder
    public Review(Long bookId, Long reviewerId, String contents, Integer score) {
        this.bookId = bookId;
        this.reviewerId = reviewerId;
        this.contents = contents;
        this.score = score;
    }

    public void update(UpdateReviewRequest dto) {
        if (dto.getScore() != null) {
            this.score = dto.getScore();
        }
        if (dto.getContents() != null) {
            this.contents = dto.getContents();
        }
    }

}
