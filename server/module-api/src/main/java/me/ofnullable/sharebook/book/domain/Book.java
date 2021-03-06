package me.ofnullable.sharebook.book.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.book.domain.converter.BookStatusConverter;
import me.ofnullable.sharebook.common.domain.Auditable;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Category category;

    private String description;

    @Convert(converter = BookStatusConverter.class)
    private BookStatus status;

    @ManyToOne(optional = false)
    private Account owner;

    @Column(nullable = false)
    private String imageUri;

    private Long currentBorrowerId;

    @Builder
    public Book(String title, String author, String publisher, Category category, String description, Account owner, String imageUri) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.description = description;
        this.owner = owner;
        this.imageUri = imageUri;
        this.status = BookStatus.AVAILABLE;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void toUnavailable(Long renterId) {
        this.status = BookStatus.UNAVAILABLE;
        this.currentBorrowerId = renterId;
    }

    public void toAvailable() {
        this.status = BookStatus.AVAILABLE;
        this.currentBorrowerId = null;
    }

    public boolean isAvailable() {
        return this.status == BookStatus.AVAILABLE;
    }

}
