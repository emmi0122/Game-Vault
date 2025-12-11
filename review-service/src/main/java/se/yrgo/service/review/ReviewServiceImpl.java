package se.yrgo.service.review;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import se.yrgo.data.ReviewRepository;
import se.yrgo.domain.Review;
import se.yrgo.dto.ProfileDTO;
import se.yrgo.dto.ProfileResponseDTO;
import se.yrgo.dto.ReviewResponseDTO;
import se.yrgo.exception.ReviewCreationException;
import se.yrgo.exception.ReviewNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepo;
    private final RestClient restClient;

    @Value("${profile.service.url}")
    private String profileServiceUrl;

    public ReviewServiceImpl(ReviewRepository reviewRepo, RestClient restClient) {
        this.reviewRepo = reviewRepo;
        this.restClient = restClient;
    }

    @Override
    public void createReview(Review review) {
        try {
            ProfileResponseDTO response = restClient.get()
                    .uri(profileServiceUrl + "/getProfile?profileId=" + review.getProfileId())
                    .retrieve()
                    .body(ProfileResponseDTO.class);

            if (response.profile() == null) {
                throw new RuntimeException();
            }
            reviewRepo.save(review);
        } catch (Exception e) {
            throw new ReviewCreationException("Could not create review", e);
        }
    }

    // TODO: This doesn't work for now.
    @Override
    public void updateReview(Review review) {
        Optional<Review> review1 = reviewRepo.findById(review.getId());
        if (review1.isPresent()) {
            review1.ifPresent(value -> value.setText(review.getText()));
            review1.ifPresent(value -> value.setRating(review.getRating()));
            reviewRepo.save(review1.get());
        }
    }

    // TODO: Check if this works
    @Override
    public void deleteReview(Long reviewId) {
            Review foundReview = reviewRepo.findById(reviewId).orElseThrow(() -> (new ReviewNotFoundException("No review found" + reviewId)));
            reviewRepo.delete(foundReview);
    }

    @Override
    public List<ReviewResponseDTO> findAllReviewsForGame(Long gameId) {
        List<Review> reviews = reviewRepo.findAllReviewsForGame(gameId);

        return reviews.stream().map(review -> {
            ProfileResponseDTO response = restClient.get()
                    .uri(profileServiceUrl + "/getProfile?profileId=" + review.getProfileId())
                    .retrieve()
                    .body(ProfileResponseDTO.class);

            ProfileDTO profile = response.profile();

            ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
            return reviewResponseDTO.toDto(review, profile);
        }).toList();
    }

    @Override
    public List<Review> findAllReviewsForProfile(Long profileId) {
        return reviewRepo.findAllReviewsForProfile(profileId);
    }


}
