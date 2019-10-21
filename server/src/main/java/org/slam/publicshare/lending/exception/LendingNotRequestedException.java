package org.slam.publicshare.lending.exception;

import lombok.Getter;

@Getter
public class LendingNotRequestedException extends RuntimeException {
    private Long LendingId;
    public LendingNotRequestedException(Long LendingId) {
        this.LendingId = LendingId;
    }
}
