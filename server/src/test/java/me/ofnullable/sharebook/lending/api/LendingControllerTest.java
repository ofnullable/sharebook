package me.ofnullable.sharebook.lending.api;

import me.ofnullable.sharebook.common.dto.PageRequest;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import me.ofnullable.sharebook.config.WithAuthenticationPrincipal;
import me.ofnullable.sharebook.lending.domain.LendingStatus;
import me.ofnullable.sharebook.lending.exception.*;
import me.ofnullable.sharebook.lending.service.LendingFindService;
import me.ofnullable.sharebook.lending.service.LendingSaveService;
import me.ofnullable.sharebook.lending.service.LendingUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static me.ofnullable.sharebook.lending.utils.LendingUtils.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class LendingControllerTest extends WithAuthenticationPrincipal {

    @InjectMocks
    private LendingController lendingController;

    @Mock
    private LendingFindService lendingFindService;

    @Mock
    private LendingSaveService lendingSaveService;

    @Mock
    private LendingUpdateService lendingUpdateService;

    private MockMvc mvc;

    @BeforeEach
    void setup() {
        this.mvc = super.setup(lendingController);
    }

    @Test
    @DisplayName("현재 계정의 대여목록 조회")
    void find_lending_by_account_id() throws Exception {
        given(lendingFindService.findAllByAccountIdAndCurrentStatus(any(Long.class), any(LendingStatus.class), any(PageRequest.class)))
                .willReturn(buildPageLending(20));

        mvc.perform(get("/lending/ACCEPTED?page=1&size=20"))
                .andExpect(jsonPath("$.totalElements", is(3)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("대여목록이 존재하지 않는 경우")
    void find_lending_by_invalid_account_id() throws Exception {
        given(lendingFindService.findAllByAccountIdAndCurrentStatus(any(Long.class), any(LendingStatus.class), any(PageRequest.class)))
                .willReturn(Page.empty());

        mvc.perform(get("/lending/ACCEPTED?page=1&size=20"))
                .andExpect(jsonPath("$.totalElements", is(0)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("도서 Id로 최근 대여기록 조회")
    void find_latest_lending_by_book_id() throws Exception {
        var requestedLending = buildRequestedLending();

        given(lendingFindService.findLatestByBookId(any(Long.class)))
                .willReturn(requestedLending);

        mvc.perform(get("/lending/book/1/latest"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("도서 Id에 해당하는 대여기록 존재하지 않는 경우 - LendingHistoryNotExistsException")
    void find_first_lending_by_invalid_book_id() throws Exception {
        given(lendingFindService.findLatestByBookId(any(Long.class)))
                .willThrow(LendingHistoryNotExistsException.class);

        mvc.perform(get("/lending/book/1/latest"))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("대여신청")
    void save_lending_with_auth() throws Exception {
        given(lendingSaveService.borrowRequest(any(Long.class), any(Long.class)))
                .willReturn(buildRequestedLending());

        mvc.perform(post("/lending/book/1"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("ACCEPTED로 대여기록 업데이트")
    void update_lending_to_accepted() throws Exception {
        given(lendingUpdateService.updateLending(any(Long.class), eq(LendingStatus.ACCEPTED)))
                .willReturn(buildAcceptedLending());

        mvc.perform(put("/lending/1/ACCEPTED"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("REJECTED로 대여기록 업데이트")
    void update_lending_to_rejected() throws Exception {
        var lending = buildRequestedLending();
        lending.rejected();

        given(lendingUpdateService.updateLending(any(Long.class), eq(LendingStatus.REJECTED)))
                .willReturn(lending);

        mvc.perform(put("/lending/1/REJECTED"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("RETURNED로 대여기록 업데이트")
    void update_lending_to_returned() throws Exception {
        var lending = buildAcceptedLending();
        lending.returned();

        given(lendingUpdateService.updateLending(any(Long.class), eq(LendingStatus.RETURNED)))
                .willReturn(lending);

        mvc.perform(put("/lending/1/RETURNED"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("REQUESTED로 대여기록 업데이트 - 400")
    void update_lending_to_requested() throws Exception {
        given(lendingUpdateService.updateLending(any(Long.class), eq(LendingStatus.REQUESTED)))
                .willThrow(new LendingStatusInvalidException(LendingStatus.NONE, LendingStatus.REQUESTED));

        mvc.perform(put("/lending/1/REQUESTED"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("이미 종료 된 대여 업데이트 시 - 400")
    void update_already_completed_lending() throws Exception {
        given(lendingUpdateService.updateLending(any(Long.class), eq(LendingStatus.REJECTED)))
                .willThrow(LendingAlreadyCompletionException.class);

        mvc.perform(put("/lending/1/REJECTED"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("요청되지 않은 대여 업데이트 시 - 400")
    void update_not_requested_lending() throws Exception {
        given(lendingUpdateService.updateLending(any(Long.class), eq(LendingStatus.REJECTED)))
                .willThrow(LendingNotRequestedException.class);

        mvc.perform(put("/lending/1/REJECTED"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("이전 상태와 같은 상태로 변경 요청 시 - 400")
    void update_lending_to_same_status() throws Exception {
        given(lendingUpdateService.updateLending(any(Long.class), eq(LendingStatus.REJECTED)))
                .willThrow(LendingStatusEqualsException.class);

        mvc.perform(put("/lending/1/REJECTED"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("존재하지 않는 대여 업데이트 시 - 404")
    void update_invalid_lending() throws Exception {
        given(lendingUpdateService.updateLending(any(Long.class), eq(LendingStatus.REJECTED)))
                .willThrow(ResourceNotFoundException.class);

        mvc.perform(put("/lending/1/REJECTED"))
                .andExpect(status().isNotFound());
    }

}
