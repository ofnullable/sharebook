package me.ofnullable.sharebook.review.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.account.domain.Account;
import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.dto.SaveReviewRequest;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewSaveService {

    private final ReviewRepository reviewRepository;
    private final ReviewValidator reviewValidator;

    @Transactional
    public Review save(SaveReviewRequest dto, Account account) {
        var entity = dto.toEntity(account);
        reviewValidator.isValidRequest(entity);
        return reviewRepository.save(entity);
    }

}
