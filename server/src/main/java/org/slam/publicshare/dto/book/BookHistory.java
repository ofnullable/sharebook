package org.slam.publicshare.dto.book;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class BookHistory {

    private Long          id;
    private Long          bookId;
    private BookStatus    requestedStatus;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String        modifiedBy;
    private LocalDateTime modifiedAt;
    private String        requestedUser;
    private LocalDateTime requestedAt;

    public String getRequestedAt() {
        return this.requestedAt != null ? this.requestedAt.format(DateTimeFormatter.ofPattern("yy-MM-dd hh:mm")) : null;
    }

    public String getStartedAt() {
        return this.startedAt != null ? this.startedAt.format(DateTimeFormatter.ofPattern("yy-MM-dd hh:mm")) : null;
    }

}
