package me.ofnullable.sharebook.lending.service;

import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import me.ofnullable.sharebook.lending.exception.LendingHistoryNotExistsException;
import me.ofnullable.sharebook.lending.repository.LendingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static me.ofnullable.sharebook.common.utils.PageRequestUtils.buildPageRequest;
import static me.ofnullable.sharebook.lending.utils.LendingUtils.*;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class LendingFindServiceTest {

    @InjectMocks
    private LendingFindService lendingFindService;

    @Mock
    private LendingRepository lendingRepository;

    private final Lending requested = buildRequestedLending();

    @Test
    @DisplayName("대여 Id로 대여기록 조회")
    void find_by_id() {
        given(lendingRepository.findById(any(Long.class)))
                .willReturn(Optional.of(requested));

        var result = lendingFindService.findById(1L);

        equalLending(result, requested);
    }

    @Test
    @DisplayName("대여기록 존재하지 않는 경우 - ResourceNotFoundException")
    void find_by_invalid_id() {
        given(lendingRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> lendingFindService.findById(1L));
    }

    @Test
    @DisplayName("도서 Id로 최근 대여기록 조회")
    void find_latest_by_book_id() {
        given(lendingRepository.findFirstByBookIdOrderByIdDesc(any(Long.class)))
                .willReturn(Optional.of(requested));

        var result = lendingFindService.findLatestByBookId(1L);

        equalLending(result, requested);
    }

    @Test
    @DisplayName("도서 Id에 해당하는 대여기록 존재하지 않는 경우 - LendingHistoryNotExistsException")
    void find_first_by_invalid_book_id() {
        given(lendingRepository.findFirstByBookIdOrderByIdDesc(any(Long.class)))
                .willReturn(Optional.empty());

        assertThrows(LendingHistoryNotExistsException.class, () -> lendingFindService.findLatestByBookId(1L));
    }

    @Test
    @DisplayName("특정 계정의 대여기록 조회")
    void find_by_account_id() {
        given(lendingRepository.findAllByBorrowerIdAndCurrentStatus(any(Long.class), any(LendingStatus.class), any(Pageable.class)))
                .willReturn(buildPageLending(20));

        var result = lendingFindService.findLendingRequestsByCurrentStatus(1L, LendingStatus.ACCEPTED, buildPageRequest(20));

        assertEquals(result.getTotalElements(), 3);
    }

    @Test
    @DisplayName("존재하지 않는 계정의 대여기록 조회시 결과 0개")
    void find_by_invalid_account_id() {
        given(lendingRepository.findAllByBorrowerIdAndCurrentStatus(any(Long.class), any(LendingStatus.class), any(Pageable.class)))
                .willReturn(Page.empty());

        var result = lendingFindService.findLendingRequestsByCurrentStatus(1L, LendingStatus.ACCEPTED, buildPageRequest(20));

        assertEquals(result.getTotalElements(), 0);
    }

    @Test
    @DisplayName("대여자 Id와 도서 Id로 최근 대여기록 조회")
    void find_latest_lending_by_borrower_id_and_book_id() {
        var lending = buildLending(1L);
        given(lendingRepository.findFirstByBorrowerIdAndBookId(any(Long.class), any(Long.class)))
                .willReturn(Optional.of(lending));

        var result = lendingFindService.findLatestByBorrowerIdAndBookId(1L, 1L);

        then(result.getId())
                .isEqualTo(lending.getId());
    }

    @Test
    @DisplayName("대여자 Id와 도서 Id로 최근 대여기록 조회 - 존재하지 않는 경우 LendingHistoryNotExistsException")
    void find_latest_lending_by_invalid_borrower_id_and_book_id() {
        given(lendingRepository.findFirstByBorrowerIdAndBookId(any(Long.class), any(Long.class)))
                .willReturn(Optional.empty());

        var exception = catchThrowable(() -> lendingFindService.findLatestByBorrowerIdAndBookId(1L, 1L));

        then(exception)
                .isInstanceOf(LendingHistoryNotExistsException.class);
    }

    @Test
    @DisplayName("내 도서의 상태별 대여 기록 조회")
    void find_lending_requests_for_my_book() {
        given(lendingRepository.findAllByBookOwnerIdAndCurrentStatus(any(Long.class), any(LendingStatus.class), any(Pageable.class)))
                .willReturn(buildPageLending(10));

        var result = lendingFindService.findLendingRequestsForMyBooksByCurrentStatus(1L, LendingStatus.REQUESTED, buildPageRequest(10));

        then(result)
                .isInstanceOf(Page.class);
        then(result.getSize())
                .isEqualTo(10);
    }

}
