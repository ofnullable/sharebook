package me.ofnullable.sharebook.lending.service;

import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import me.ofnullable.sharebook.lending.exception.LendingAlreadyCompletionException;
import me.ofnullable.sharebook.lending.exception.LendingStatusEqualsException;
import me.ofnullable.sharebook.lending.exception.LendingStatusInvalidException;
import me.ofnullable.sharebook.lending.exception.NoSuchLendingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static me.ofnullable.sharebook.lending.utils.LendingUtils.buildAcceptedLending;
import static me.ofnullable.sharebook.lending.utils.LendingUtils.buildRequestedLending;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class LendingUpdateServiceTest {

    @InjectMocks
    private LendingUpdateService lendingUpdateService;

    @Mock
    private LendingFindService lendingFindService;

    private Lending requested = buildRequestedLending();
    private Lending accepted = buildAcceptedLending();

    @Test
    @DisplayName("ACCEPTED로 status 업데이트")
    public void update_to_accepted() {
        given(lendingFindService.findById(any(Long.class)))
                .willReturn(requested);

        var result = lendingUpdateService.updateLending(1L, LendingStatus.ACCEPTED);

        assertEquals(result.getHistories().size(), 2);
        assertEquals(result.getHistories().get(1).getStatus(), LendingStatus.ACCEPTED);
    }

    @Test
    @DisplayName("CANCELED로 status 업데이트")
    public void update_to_canceled() {
        given(lendingFindService.findById(any(Long.class)))
                .willReturn(requested);

        var result = lendingUpdateService.updateLending(1L, LendingStatus.CANCELED);

        assertEquals(result.getHistories().size(), 2);
        assertEquals(result.getHistories().get(1).getStatus(), LendingStatus.CANCELED);
    }

    @Test
    @DisplayName("REJECTED로 status 업데이트")
    public void update_to_rejected() {
        given(lendingFindService.findById(any(Long.class)))
                .willReturn(requested);

        var result = lendingUpdateService.updateLending(1L, LendingStatus.REJECTED);

        assertEquals(result.getHistories().size(), 2);
        assertEquals(result.getHistories().get(1).getStatus(), LendingStatus.REJECTED);
    }

    @Test
    @DisplayName("RETURNED로 status 업데이트")
    public void update_to_returned() {
        given(lendingFindService.findById(any(Long.class)))
                .willReturn(accepted);

        var result = lendingUpdateService.updateLending(1L, LendingStatus.RETURNED);

        assertEquals(result.getHistories().size(), 3);
        assertEquals(result.getHistories().get(2).getStatus(), LendingStatus.RETURNED);
    }

    @Test
    @DisplayName("존재하지 않는 대여기록 업데이트 시 - NoSuchLendingException")
    public void update_not_requested_lending() {
        given(lendingFindService.findById(any(Long.class)))
                .willThrow(NoSuchLendingException.class);

        assertThrows(NoSuchLendingException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.ACCEPTED));
    }

    @Test
    @DisplayName("이전 상태와 같은 상태로 변경 요청 시 - LendingStatusEqualsException")
    public void update_to_same_status() {
        var lending = buildAcceptedLending();

        given(lendingFindService.findById(any(Long.class)))
                .willReturn(lending);

        assertThrows(LendingStatusEqualsException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.ACCEPTED));
    }

    @Test
    @DisplayName("REQUESTED 상태를 유효하지 않은 상태로 변경 요청 - LendingStatusInvalidException")
    public void update_requested_to_invalid_status() {
        var requestedLending = buildRequestedLending();

        given(lendingFindService.findById(any(Long.class)))
                .willReturn(requestedLending);

        assertThrows(LendingStatusInvalidException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.RETURNED));
    }

    @Test
    @DisplayName("ACCEPTED 상태를 유효하지 않은 상태로 변경 요청 - LendingStatusInvalidException")
    public void update_accepted_to_invalid_status() {
        var acceptedLending = buildAcceptedLending();

        given(lendingFindService.findById(any(Long.class)))
                .willReturn(acceptedLending);

        assertThrows(LendingStatusInvalidException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.REJECTED));
        assertThrows(LendingStatusInvalidException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.CANCELED));
    }

    @Test
    @DisplayName("CANCELED 상태를 유효하지 않은 상태로 변경 요청 - LendingAlreadyCompletionException")
    public void update_canceled_to_invalid_status() {
        var canceledLending = buildRequestedLending();
        canceledLending.canceled();

        given(lendingFindService.findById(any(Long.class)))
                .willReturn(canceledLending);

        assertThrows(LendingAlreadyCompletionException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.ACCEPTED));
        assertThrows(LendingAlreadyCompletionException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.REJECTED));
        assertThrows(LendingAlreadyCompletionException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.RETURNED));
    }

    @Test
    @DisplayName("REJECTED 상태를 유효하지 않은 상태로 변경 요청 - LendingAlreadyCompletionException")
    public void update_rejected_to_invalid_status() {
        var rejectedLending = buildRequestedLending();
        rejectedLending.rejected();

        given(lendingFindService.findById(any(Long.class)))
                .willReturn(rejectedLending);

        assertThrows(LendingAlreadyCompletionException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.ACCEPTED));
        assertThrows(LendingAlreadyCompletionException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.CANCELED));
        assertThrows(LendingAlreadyCompletionException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.RETURNED));
    }

    @Test
    @DisplayName("RETURNED 상태를 유효하지 않은 상태로 변경 요청 - LendingAlreadyCompletionException")
    public void update_returned_to_invalid_status() {
        var returnedLending = buildAcceptedLending();
        returnedLending.returned();

        given(lendingFindService.findById(any(Long.class)))
                .willReturn(returnedLending);

        assertThrows(LendingAlreadyCompletionException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.ACCEPTED));
        assertThrows(LendingAlreadyCompletionException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.REJECTED));
        assertThrows(LendingAlreadyCompletionException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.CANCELED));
    }

    @Test
    @DisplayName("REQUESTED로 상태 변경 시 - LendingStatusInvalidException")
    public void update_to_requested() {
        given(lendingFindService.findById(any(Long.class)))
                .willReturn(requested);

        assertThrows(LendingStatusInvalidException.class, () -> lendingUpdateService.updateLending(1L, LendingStatus.REQUESTED));
    }

}
