package me.ofnullable.sharebook.review.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.config.WithAuthenticationPrincipal;
import me.ofnullable.sharebook.review.dto.SaveReviewRequest;
import me.ofnullable.sharebook.review.service.ReviewFindService;
import me.ofnullable.sharebook.review.service.ReviewSaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static me.ofnullable.sharebook.review.utils.ReviewUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ReviewControllerTest extends WithAuthenticationPrincipal {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewFindService reviewFindService;

    @Mock
    private ReviewSaveService reviewSaveService;

    private MockMvc mvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        this.mvc = super.setup(reviewController);
    }

    @Test
    void find_review_by_book_id() throws Exception {
        given(reviewFindService.findAllByBookId(any(Long.class)))
                .willReturn(buildReviewList());

        mvc.perform(get("/reviews/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    void save_review() throws Exception {
        given(reviewSaveService.save(any(SaveReviewRequest.class), any(Account.class)))
                .willReturn(buildReview());

        mvc.perform(post("/review")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(buildSaveRequest())))
                .andExpect(status().isCreated());
    }

}