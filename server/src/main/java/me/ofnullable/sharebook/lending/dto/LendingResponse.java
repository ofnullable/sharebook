package me.ofnullable.sharebook.lending.dto;

import lombok.Getter;
import me.ofnullable.sharebook.book.dto.book.BookResponse;
import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.domain.LendingStatus;

@Getter
public class LendingResponse {

    private Long id;
    private Long borrowerId;
    private BookResponse book;
    private LendingStatus currentStatus;

    public LendingResponse(Lending lending) {
        this.id = lending.getId();
        this.borrowerId = lending.getBorrowerId();
        this.book = new BookResponse(lending.getBook());
        this.currentStatus = lending.getCurrentStatus();
    }

}
