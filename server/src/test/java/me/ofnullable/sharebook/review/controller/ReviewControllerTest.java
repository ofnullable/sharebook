package me.ofnullable.sharebook.review.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.config.WithAuthenticationPrincipal;
import me.ofnullable.sharebook.review.dto.SaveReviewRequest;
import me.ofnullable.sharebook.review.dto.UpdateReviewRequest;
import me.ofnullable.sharebook.review.service.ReviewDeleteService;
import me.ofnullable.sharebook.review.service.ReviewFindService;
import me.ofnullable.sharebook.review.service.ReviewSaveService;
import me.ofnullable.sharebook.review.service.ReviewUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static me.ofnullable.sharebook.review.utils.ReviewUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ReviewControllerTest extends WithAuthenticationPrincipal {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewFindService reviewFindService;

    @Mock
    private ReviewSaveService reviewSaveService;

    @Mock
    private ReviewUpdateService reviewUpdateService;

    @Mock
    private ReviewDeleteService reviewDeleteService;

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        this.mvc = super.setup(reviewController);
    }

    @Test
    @DisplayName("도서 Id와 연관된 리뷰 조회")
    void find_review_by_book_id() throws Exception {
        given(reviewFindService.findAllByBookId(any(Long.class)))
                .willReturn(buildReviewList());

        mvc.perform(get("/reviews/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("현재 로그인된 계정의 Id로 리뷰목록 조회")
    void find_all_by_my_reviews() throws Exception {
        given(reviewFindService.findAllByReviewerId(any(Long.class)))
                .willReturn(buildReviewList());

        mvc.perform(get("/account/0/reviews"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("작성자 Id로 리뷰목록 조회")
    void find_all_by_reviewer_id() throws Exception {
        given(reviewFindService.findAllByReviewerId(any(Long.class)))
                .willReturn(buildReviewList());

        mvc.perform(get("/account/1/reviews"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("리뷰를 작성한적 없는 유저의 리뷰목록 조회")
    void find_all_by_invalid_reviewer_id() throws Exception {
        given(reviewFindService.findAllByReviewerId(any(Long.class)))
                .willReturn(Collections.emptyList());

        mvc.perform(get("/account/1/reviews"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("리뷰 등록")
    void save_review() throws Exception {
        given(reviewSaveService.save(any(SaveReviewRequest.class), any(Account.class)))
                .willReturn(buildReview());

        mvc.perform(post("/review")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(buildSaveRequest())))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("리뷰 업데이트")
    void update_review() throws Exception {
        given(reviewUpdateService.updateReview(any(UpdateReviewRequest.class)))
                .willReturn(buildReview());

        mvc.perform(put("/review/1")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(buildUpdateRequest())))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("리뷰 삭제")
    void delete_review() throws Exception {
        given(reviewDeleteService.deleteReview(any(Long.class)))
                .willReturn(1L);

        mvc.perform(delete("/review/1"))
                .andExpect(status().isOk());
    }

}