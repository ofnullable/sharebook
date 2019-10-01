package org.slam.publicshare.rental.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slam.publicshare.config.WithAuthenticationPrincipal;
import org.slam.publicshare.rental.domain.RentalStatus;
import org.slam.publicshare.rental.exception.RentalStatusInvalidException;
import org.slam.publicshare.rental.service.RentalFindService;
import org.slam.publicshare.rental.service.RentalSaveService;
import org.slam.publicshare.rental.service.RentalUpdateService;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.slam.publicshare.rental.utils.RentalUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class RentalControllerTest extends WithAuthenticationPrincipal {

    private MockMvc mvc;

    @InjectMocks
    private RentalController rentalController;

    @Mock
    private RentalFindService rentalFindService;

    @Mock
    private RentalSaveService rentalSaveService;

    @Mock
    private RentalUpdateService rentalUpdateService;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.mvc = super.setup(rentalController);
    }

    @Test
    @DisplayName("특정 도서의 대여목록 조회")
    public void find_rental_by_book_id() throws Exception {
        given(rentalFindService.findAllByBookId(any(Long.class)))
                .willReturn(buildRentalList());

        mvc.perform(get("/book/1/rental"))
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 도서의 대여목록 조회")
    public void find_rental_by_invalid_book_id() throws Exception {
        given(rentalFindService.findAllByBookId(any(Long.class)))
                .willReturn(new ArrayList<>());

        mvc.perform(get("/book/1/rental"))
                .andExpect(jsonPath("$.length()", is(0)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("특정 계정의 대여목록 조회")
    public void find_rental_by_account_id() throws Exception {
        given(rentalFindService.findAllByAccountId(any(Long.class)))
                .willReturn(buildRentalList());

        mvc.perform(get("/account/rental"))
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("존재하지 않는 계정의 대여목록 조회")
    public void find_rental_by_invalid_account_id() throws Exception {
        given(rentalFindService.findAllByAccountId(any(Long.class)))
                .willReturn(new ArrayList<>());

        mvc.perform(get("/account/rental"))
                .andExpect(jsonPath("$.length()", is(0)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("대여신청")
    public void save_rental_with_auth() throws Exception {
        given(rentalSaveService.rentalRequest(any(Long.class), any(Long.class)))
                .willReturn(buildRequestedRental());

        mvc.perform(post("/book/1/rental"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("ACCEPTED로 대여기록 업데이트")
    public void update_rental_to_accepted() throws Exception {
        given(rentalUpdateService.updateRental(any(Long.class), eq(RentalStatus.ACCEPTED)))
                .willReturn(buildAcceptedRental());

        mvc.perform(put("/rental/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(RentalStatus.ACCEPTED)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("REJECTED로 대여기록 업데이트")
    public void update_rental_to_rejected() throws Exception {
        var rental = buildRequestedRental();
        rental.reject();

        given(rentalUpdateService.updateRental(any(Long.class), eq(RentalStatus.REJECTED)))
                .willReturn(rental);

        mvc.perform(put("/rental/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(RentalStatus.REJECTED)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("RETURNED로 대여기록 업데이트")
    public void update_rental_to_returned() throws Exception {
        var rental = buildAcceptedRental();
        rental.returned();

        given(rentalUpdateService.updateRental(any(Long.class), eq(RentalStatus.RETURNED)))
                .willReturn(rental);

        mvc.perform(put("/rental/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(RentalStatus.RETURNED)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("REQUESTED로 대여기록 업데이트 - 400")
    public void update_rental_to_requested() throws Exception {
        given(rentalUpdateService.updateRental(any(Long.class), eq(RentalStatus.REQUESTED)))
                .willThrow(RentalStatusInvalidException.class);

        mvc.perform(put("/rental/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(RentalStatus.REQUESTED)))
                .andExpect(status().isBadRequest());
    }

}