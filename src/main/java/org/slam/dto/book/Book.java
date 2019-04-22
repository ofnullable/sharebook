package org.slam.dto.book;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class Book {

    private Long          id;
    private String        title;
    private String        author;
    private String        description;
    private String        mainImage;
    private BookStatus    status;
    private String        modifiedBy;
    private LocalDateTime modifiedAt;
    private String        createdBy;
    private LocalDateTime createdAt;

    private String            RecentLoaner;
    private Integer           waiters;
    private Boolean           isReserved;
    private Long              myHistoryId;
    private BookStatus        originStatus;
    private OwnerAnswer       ownerAnswer;
    private List<BookImage>   images;
    private List<BookHistory> histories;

    public String getCreatedAt() {
        if (this.createdAt != null) {
            return this.createdAt.format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        } else {
            return null;
        }
    }

}