package org.slam.dto.book;

import lombok.*;

import java.time.LocalDateTime;

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

}
