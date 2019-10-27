package me.ofnullable.sharebook.lending.exception;

import lombok.Getter;

@Getter
public class NoSuchLendingException extends RuntimeException {
    private Long LendingId;
    public NoSuchLendingException(Long LendingId) {
        this.LendingId = LendingId;
    }
}
