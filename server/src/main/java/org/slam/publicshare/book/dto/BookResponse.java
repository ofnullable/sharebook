package org.slam.publicshare.book.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.book.domain.Book;
import org.slam.publicshare.book.domain.BookImage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String description;
    private String owner;
    private List<String> images;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private String createdBy;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.description = book.getDescription();
        this.owner = book.getOwner();
        this.images = book.getImages().stream().map(BookImage::getImageUrl).collect(Collectors.toList());
        this.modifiedAt = book.getModifiedAt();
        this.modifiedBy = book.getModifiedBy();
        this.createdAt = book.getCreatedAt();
        this.createdBy = book.getCreatedBy();
    }
}
