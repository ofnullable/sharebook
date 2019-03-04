package org.slam.dto.book;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class BookHistory {

    private Long id;
    private Long bookId;
    private BookStatus requestedStatus;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String requestedUser;
    private LocalDateTime requestedAt;

    private BookStatus originStatus;

    public String getRequestedAt() {
        if (this.requestedAt != null) {
            return this.requestedAt.format(DateTimeFormatter.ofPattern("yy-MM-dd hh:mm"));
        } else {
            return null;
        }
    }

}
