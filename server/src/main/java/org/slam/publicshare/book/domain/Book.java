package org.slam.publicshare.book.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.account.domain.Account;
import org.slam.publicshare.book.domain.converter.BookStatusConverter;
import org.slam.publicshare.common.domain.Auditable;

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
    private String imageUrl;

    @Builder
    public Book(String title, String author, String publisher, Category category, String description, BookStatus status, Account owner, String imageUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.description = description;
        this.status = status;
        this.owner = owner;
        this.imageUrl = imageUrl;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void changeStatus(BookStatus status) {
        this.status = status;
    }

}
