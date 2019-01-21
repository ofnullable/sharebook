package org.slam.dto.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter @Setter @ToString
public class Book {

    private Long id;
    private String title;
    private String author;
    private String description;
    private BookStatus status;
    private String modifiedBy;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private LocalDateTime createdAt;

    private String RecentLoaner;
    private Integer waiters;
    private List<BookImage> images;
    private List<BookHistory> histories;

    public Book(String title, String author, List<BookImage> images) {
        this.title = title;
        this.author = author;
        this.images = images;
    }

    public Book(Long id, BookStatus status, String modifiedBy) {
        this.id = id;
        this.status = status;
        this.modifiedBy = modifiedBy;
    }

}