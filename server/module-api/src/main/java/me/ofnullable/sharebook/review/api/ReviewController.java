package me.ofnullable.sharebook.review.api;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.common.dto.PageRequest;
import me.ofnullable.sharebook.review.dto.MyReviewResponse;
import me.ofnullable.sharebook.review.dto.ReviewResponse;
import me.ofnullable.sharebook.review.dto.SaveReviewRequest;
import me.ofnullable.sharebook.review.dto.UpdateReviewRequest;
import me.ofnullable.sharebook.review.service.ReviewDeleteService;
import me.ofnullable.sharebook.review.service.ReviewFindService;
import me.ofnullable.sharebook.review.service.ReviewSaveService;
import me.ofnullable.sharebook.review.service.ReviewUpdateService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewFindService reviewFindService;
    private final ReviewSaveService reviewSaveService;
    private final ReviewUpdateService reviewUpdateService;
    private final ReviewDeleteService reviewDeleteService;

    @GetMapping("/reviews/book/{bookId}")
    public List<ReviewResponse> findAllReviewsByBookId(@PathVariable Long bookId) {
        return reviewFindService.findAllByBookId(bookId)
                .stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/review")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse saveReview(
            @AuthenticationPrincipal(expression = "account") Account account,
            @RequestBody @Valid SaveReviewRequest dto) {
        return new ReviewResponse(reviewSaveService.save(dto, account));
    }

    @PutMapping("/review/{id}")
    public ReviewResponse updateReview(@RequestBody @Valid UpdateReviewRequest dto) {
        return new ReviewResponse(reviewUpdateService.updateReview(dto));
    }

    @DeleteMapping("/review/{id}")
    public Long removeReview(@PathVariable Long id) {
        return reviewDeleteService.deleteReview(id);
    }

    @GetMapping("/account/{accountId}/reviews")
    public Page<MyReviewResponse> findReviewsByAccountId(
            @AuthenticationPrincipal(expression = "account") Account account,
            @Valid PageRequest pageRequest,
            @PathVariable Long accountId) {
        if (accountId == 0) {
            return reviewFindService.findAllByReviewerId(account.getId(), pageRequest);
        }
        return reviewFindService.findAllByReviewerId(accountId, pageRequest);
    }

}
