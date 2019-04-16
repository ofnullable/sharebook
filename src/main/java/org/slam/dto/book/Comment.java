package org.slam.dto.book;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class Comment {

    private Long          id;
    private Long          bookId;
    private Long          parentId;
    private int           depth;
    private int           groupOrder;
    private String        comment;
    private String        createdBy;
    private LocalDateTime createdAt;

    public String getCreatedAt() {
        return this.createdAt != null ? this.createdAt.format(DateTimeFormatter.ofPattern("yy-MM-dd hh:mm:ss")) : null;
    }

}
