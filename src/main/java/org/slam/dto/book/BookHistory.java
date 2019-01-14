package org.slam.dto.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class BookHistory {

    private Long bookId;
    private BookStatus requestedStatus;
    private String requestedUser;
    private LocalDateTime requestedAt;

}
