package se.yrgo.service.review;

import org.springframework.stereotype.Service;
import se.yrgo.data.ReviewRepository;
import se.yrgo.domain.Review;
import se.yrgo.exception.ReviewCreationException;
import se.yrgo.exception.ReviewNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepo;

    public ReviewServiceImpl(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public void createReview(Review review) {
        try {
            reviewRepo.save(review);
        } catch (Exception e) {
            throw new ReviewCreationException("Could not create review", e);
        }
    }

    @Override
    public void updateReview(Review review) {
        Optional<Review> review1 = reviewRepo.findById(review.getId());
        if (review1.isPresent()) {
            review1.ifPresent(value -> value.setText(review.getText()));
            review1.ifPresent(value -> value.setRating(review.getRating()));
            reviewRepo.save(review1.get());
        }
    }

    @Override
    public void deleteReview(Long reviewId) {
            Review foundReview = reviewRepo.findById(reviewId).orElseThrow(() -> (new ReviewNotFoundException("No review found" + reviewId)));
            reviewRepo.delete(foundReview);
    }

    @Override
    public List<Review> findAllReviewsForGame(Long gameId) {
        return reviewRepo.findAllReviewsForGame(gameId);
    }
}
