package se.yrgo.service.reviewLikes;

import org.springframework.stereotype.Service;
import se.yrgo.data.ReviewLikesRepository;
import se.yrgo.data.ReviewRepository;
import se.yrgo.domain.Review;
import se.yrgo.domain.ReviewLikes;
import se.yrgo.exception.ProfileNotFound;
import se.yrgo.exception.ReviewNotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewLikesServiceImpl implements ReviewLikesService {
    private final ReviewLikesRepository reviewLikesRepository;
    private final ReviewRepository reviewRepository;

    public ReviewLikesServiceImpl(ReviewLikesRepository rlr, ReviewRepository rr) {
        this.reviewLikesRepository = rlr;
        this.reviewRepository = rr;
    }

    public void addLike(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Couldn't find review"));
        ReviewLikes like = new ReviewLikes();
        like.setReview(review);
        like.setLikedAt(Instant.now());
        like.setProfileId(review.getProfileId());
        review.getLikes().add(like);
        reviewLikesRepository.save(like);
    }

    public void deleteLike(Long reviewId, Long profileId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Couldn't find review"));
        ReviewLikes like = review.getLikes().stream()
                .filter(l -> l.getProfileId().equals(profileId))
                .findFirst()
                .orElseThrow(() -> new ProfileNotFound("Couldn't find profile"));
        review.getLikes().remove(like);
        reviewLikesRepository.delete(like);

    }

}
