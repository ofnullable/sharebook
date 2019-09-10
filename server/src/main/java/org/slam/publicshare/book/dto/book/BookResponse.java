package org.slam.publicshare.book.dto.book;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.book.domain.Book;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String category;
    private String description;
    private String owner;
    private String imageUrl;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private String createdBy;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.category = book.getCategory().getName();
        this.description = book.getDescription();
        this.owner = book.getOwner().getName();
        this.imageUrl = book.getImageUrl();
        this.modifiedAt = book.getModifiedAt();
        this.modifiedBy = book.getModifiedBy();
        this.createdAt = book.getCreatedAt();
        this.createdBy = book.getCreatedBy();
    }

}
