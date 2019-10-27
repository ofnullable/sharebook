package me.ofnullable.sharebook.lending.domain;

import me.ofnullable.sharebook.lending.exception.LendingAlreadyCompletionException;
import me.ofnullable.sharebook.lending.exception.LendingNotRequestedException;
import me.ofnullable.sharebook.lending.exception.LendingStatusEqualsException;
import me.ofnullable.sharebook.lending.exception.LendingStatusInvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static me.ofnullable.sharebook.lending.utils.LendingUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LendingTest {

    @Test
    @DisplayName("대여요청")
    public void save_lending_history() {
        var lending = buildRequestedLending();

        assertEquals(lending.getHistories().size(), 1);
        assertEquals(lending.getHistories().get(0).getStatus(), LendingStatus.REQUESTED);
    }

    @Test
    @DisplayName("대여수락")
    public void lending_accept() {
        var lending = buildAcceptedLending();

        assertEquals(lending.getHistories().size(), 2);
        assertEquals(lending.getHistories().get(1).getStatus(), LendingStatus.ACCEPTED);
    }

    @Test
    @DisplayName("대여거절")
    public void lending_reject() {
        var lending = buildRequestedLending();
        lending.reject();

        assertEquals(lending.getHistories().size(), 2);
        assertEquals(lending.getHistories().get(1).getStatus(), LendingStatus.REJECTED);
    }

    @Test
    @DisplayName("반납")
    public void lending_return() {
        var lending = buildAcceptedLending();
        lending.returned();

        assertEquals(lending.getHistories().size(), 3);
        assertEquals(lending.getHistories().get(2).getStatus(), LendingStatus.RETURNED);
    }

    @Test
    @DisplayName("존재하지 않는 요청에 대한 처리 시 - LendingNotRequestedException")
    public void invalid_first_status() {
        var lending = buildLending(1L);

        assertThrows(LendingNotRequestedException.class, lending::accept);
        assertThrows(LendingNotRequestedException.class, lending::reject);
        assertThrows(LendingNotRequestedException.class, lending::returned);
    }

    @Test
    @DisplayName("동일한 상태로 변경 시 - LendingStatusEqualsException")
    public void same_lending_status() {
        var lending = buildRequestedLending();

        assertThrows(LendingStatusEqualsException.class, lending::borrow);

        assertThrows(LendingStatusEqualsException.class, () -> {
            lending.accept();
            lending.accept();
        });

        assertThrows(LendingStatusEqualsException.class, () -> {
            lending.returned();
            lending.returned();
        });
    }

    @Test
    @DisplayName("이미 종료된 대여기록 수정시도 시 - LendingAlreadyCompletionException")
    public void already_completed_lending() {
        var rejected = buildRequestedLending();
        rejected.reject();

        assertThrows(LendingAlreadyCompletionException.class, rejected::accept);
        assertThrows(LendingAlreadyCompletionException.class, rejected::returned);

        var returned = buildAcceptedLending();
        returned.returned();

        assertThrows(LendingAlreadyCompletionException.class, returned::accept);
        assertThrows(LendingAlreadyCompletionException.class, returned::reject);
    }

    @Test
    @DisplayName("이미 시작된 대여기록 거절 시 - LendingStatusInvalidException")
    public void already_started_lending() {
        var accepted = buildAcceptedLending();

        assertThrows(LendingStatusInvalidException.class, accepted::reject);
    }

    @Test
    @DisplayName("시작되지 않은 대여기록 반납요청 시 - LendingStatusInvalidException")
    public void change_not_started_lending_to_return() {
        var accepted = buildAcceptedLending();

        assertThrows(LendingStatusInvalidException.class, accepted::reject);
    }

}
