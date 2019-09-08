package org.slam.publicshare.book.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slam.publicshare.common.domain.Auditable;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
    private BookCategory category;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String imageUrl;

    @Builder
    public Book(String title, String author, String publisher, String description, String owner, String imageUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.owner = owner;
        this.imageUrl = imageUrl;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

}
