package me.ofnullable.sharebook.review.repository;

import me.ofnullable.sharebook.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByBookIdOrderByIdDesc(Long bookId);

    Optional<Review> findByReviewerIdAndBookId(Long reviewerId, Long bookId);

    List<Review> findAllByReviewerId(Long reviewrId);
}
