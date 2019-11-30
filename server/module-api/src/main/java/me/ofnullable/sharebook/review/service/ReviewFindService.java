package me.ofnullable.sharebook.review.service;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.common.dto.PageRequest;
import me.ofnullable.sharebook.common.exception.ResourceNotFoundException;
import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.dto.MyReviewResponse;
import me.ofnullable.sharebook.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewFindService {

    private final ReviewRepository reviewRepository;

    public List<Review> findAllByBookId(Long bookId) {
        return reviewRepository.findAllByBookIdOrderByIdDesc(bookId);
    }

    public Review findByReviewId(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Review.class));
    }

    public Page<MyReviewResponse> findAllByReviewerId(Long reviewerId, PageRequest pageRequest) {
        return reviewRepository.findAllByReviewerWithBook(reviewerId, pageRequest.of());
    }

}
