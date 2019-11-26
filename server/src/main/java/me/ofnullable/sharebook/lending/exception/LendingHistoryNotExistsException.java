package me.ofnullable.sharebook.lending.exception;

import lombok.Getter;

@Getter
public class LendingHistoryNotExistsException extends RuntimeException {

    private Long bookId;

    public LendingHistoryNotExistsException(Long bookId) {
        this.bookId = bookId;
    }

}
