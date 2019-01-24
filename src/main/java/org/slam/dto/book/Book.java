package org.slam.dto.book;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
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
    private UserAnswer ownerAnswer;
    private List<BookImage> images;
    private List<BookHistory> histories;

}