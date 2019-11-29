package me.ofnullable.sharebook.book.dto.book;

import lombok.Getter;
import me.ofnullable.sharebook.book.domain.Book;
import me.ofnullable.sharebook.book.domain.BookStatus;

import java.time.LocalDateTime;

@Getter
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String category;
    private String description;
    private BookStatus status;
    private String owner;
    private String ownerAvatar;
    private String imageUri;
    private Long currentBorrowerId;
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
        this.status = book.getStatus();
        this.owner = book.getOwner().getName();
        this.ownerAvatar = book.getOwner().getAvatar();
        this.currentBorrowerId = book.getCurrentBorrowerId();
        this.imageUri = book.getImageUri();
        this.modifiedAt = book.getModifiedAt();
        this.modifiedBy = book.getModifiedBy();
        this.createdAt = book.getCreatedAt();
        this.createdBy = book.getCreatedBy();
    }

}
