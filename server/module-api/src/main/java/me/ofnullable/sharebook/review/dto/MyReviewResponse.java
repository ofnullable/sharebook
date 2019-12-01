package me.ofnullable.sharebook.review.dto;

import java.time.LocalDateTime;

/**
 * For spring data jpa projection..
 * Join without association mapping
 */
public interface MyReviewResponse {

    Long getId();
    Long getBookId();
    String getBookTitle();
    String getBookAuthor();
    Long getAccountId();
    String getName();
    String getAvatar();
    String getContents();
    Integer getScore();
    LocalDateTime getModifiedAt();
    String getModifiedBy();
    LocalDateTime getCreatedAt();
    String getCreatedBy();

}
