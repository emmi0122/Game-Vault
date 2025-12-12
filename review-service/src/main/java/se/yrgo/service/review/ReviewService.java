package se.yrgo.service.review;

import org.springframework.web.bind.annotation.RequestParam;
import se.yrgo.domain.Review;
import se.yrgo.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    void createReview(Review review);
    void updateReview(Long id, Review review);
    void deleteReview(Long reviewId);
    List<ReviewResponseDTO> findAllReviewsForGame(Long gameId);
    List<Review> findAllReviewsForProfile(Long profileId);
}
