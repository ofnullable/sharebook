package me.ofnullable.sharebook.lending.dto;

import lombok.Getter;
import me.ofnullable.sharebook.book.dto.book.BookResponse;
import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.domain.LendingStatus;

import java.time.LocalDateTime;

@Getter
public class LendingResponse {

    private Long id;
    private Long borrowerId;
    private BookResponse book;
    private LendingStatus currentStatus;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public LendingResponse(Lending lending) {
        this.id = lending.getId();
        this.borrowerId = lending.getBorrowerId();
        this.book = new BookResponse(lending.getBook());
        this.currentStatus = lending.getCurrentStatus();
        this.startedAt = lending.getStartedAt();
        this.endedAt = lending.getEndedAt();
    }

}
