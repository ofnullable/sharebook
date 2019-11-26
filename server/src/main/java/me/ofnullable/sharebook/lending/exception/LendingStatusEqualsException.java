package me.ofnullable.sharebook.lending.exception;

import lombok.Getter;
import me.ofnullable.sharebook.lending.domain.LendingStatus;

@Getter
public class LendingStatusEqualsException extends RuntimeException {

    private LendingStatus status;

    public LendingStatusEqualsException(LendingStatus state) {
        this.status = state;
    }

}
