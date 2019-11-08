package me.ofnullable.sharebook.review.controller;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.review.dto.ReviewResponse;
import me.ofnullable.sharebook.review.dto.SaveReviewRequest;
import me.ofnullable.sharebook.review.service.ReviewFindService;
import me.ofnullable.sharebook.review.service.ReviewSaveService;
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

}
