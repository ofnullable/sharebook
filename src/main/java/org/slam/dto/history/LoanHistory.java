package org.slam.dto.history;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slam.dto.book.BookStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter @ToString
public class LoanHistory {

    private Long bookId;
    private BookStatus status;
    private String requestedUser;
    private LocalDateTime requestedAt;

}
