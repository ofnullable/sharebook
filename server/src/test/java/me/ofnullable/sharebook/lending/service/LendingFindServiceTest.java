package me.ofnullable.sharebook.lending.service;

import me.ofnullable.sharebook.lending.domain.Lending;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import me.ofnullable.sharebook.lending.exception.NoSuchLendingException;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class LendingFindServiceTest {

    @InjectMocks
    private LendingFindService lendingFindService;

    @Mock
    private LendingRepository lendingRepository;

    private Lending requested = buildRequestedLending();

    @Test
    @DisplayName("대여 Id로 대여기록 조회")
    public void find_by_id() {
        given(lendingRepository.findById(any(Long.class)))
                .willReturn(Optional.of(requested));

        var result = lendingFindService.findById(1L);

        equalLending(result, requested);
    }

    @Test
    @DisplayName("대여기록 존재하지 않는 경우 - NoSuchLendingException")
    public void find_by_invalid_id() {
        given(lendingRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        assertThrows(NoSuchLendingException.class, () -> lendingFindService.findById(1L));
    }

    @Test
    @DisplayName("특정 상태의 대여기록 조회")
    public void find_by_account_id() {
        given(lendingRepository.findAllByAccountIdAndCurrentStatus(any(Long.class), any(LendingStatus.class), any(Pageable.class)))
                .willReturn(buildPageLending(20));

        var result = lendingFindService.findAllByAccountIdAndCurrentStatus(1L, LendingStatus.ACCEPTED, buildPageRequest(20));

        assertEquals(result.getTotalElements(), 3);
    }

    @Test
    @DisplayName("존재하지 않는 계정의 대여기록 조회시 결과 0개")
    public void find_by_invalid_account_id() {
        given(lendingRepository.findAllByAccountIdAndCurrentStatus(any(Long.class), any(LendingStatus.class), any(Pageable.class)))
                .willReturn(Page.empty());

        var result = lendingFindService.findAllByAccountIdAndCurrentStatus(1L, LendingStatus.ACCEPTED, buildPageRequest(20));

        assertEquals(result.getTotalElements(), 0);
    }

}
