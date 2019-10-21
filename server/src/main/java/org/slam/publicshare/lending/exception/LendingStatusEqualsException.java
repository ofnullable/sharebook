package org.slam.publicshare.lending.exception;

import lombok.Getter;
import org.slam.publicshare.lending.domain.LendingStatus;

@Getter
public class LendingStatusEqualsException extends RuntimeException {
    private LendingStatus status;
    public LendingStatusEqualsException(LendingStatus state) {
        this.status = state;
    }
}
