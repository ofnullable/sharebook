package me.ofnullable.sharebook.review.repository;

import me.ofnullable.sharebook.review.domain.Review;
import me.ofnullable.sharebook.review.dto.MyReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByBookIdOrderByIdDesc(Long bookId);

    Optional<Review> findByReviewerAccountIdAndBookId(Long reviewerId, Long bookId);

    @Query(
            countQuery = "SELECT count(r.id) FROM Review r WHERE r.reviewer.accountId = :reviewerId",
            value = "SELECT r.id as id, r.reviewer.accountId as reviewerId, r.bookId as bookId, r.contents as contents, r.score as score, " +
                    "r.modifiedBy as modifiedBy, r.modifiedAt as modifiedAt, b.title as bookTitle, b.author as bookAuthor " +
                    "FROM Review r INNER JOIN Book b ON r.bookId = b.id WHERE r.reviewer.accountId = :reviewerId"
    )
    Page<MyReviewResponse> findAllByReviewerWithBook(@Param("reviewerId") Long reviewerId, Pageable pageable);

}
