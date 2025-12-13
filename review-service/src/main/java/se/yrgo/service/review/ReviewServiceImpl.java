package se.yrgo.service.review;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import se.yrgo.data.ReviewRepository;
import se.yrgo.domain.Review;
import se.yrgo.dto.ProfileDTO;
import se.yrgo.dto.ProfileResponseDTO;
import se.yrgo.dto.ReviewResponseDTO;
import se.yrgo.exception.ReviewCreationException;
import se.yrgo.exception.ReviewNotFoundException;

import java.time.Instant;
import java.util.List;

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
            ProfileResponseDTO response = findProfile(review.getProfileId());
            if (response == null|| response.profile() == null) {
                throw new ReviewCreationException("Couldn't find profile");
            }
            review.setCreatedAt(Instant.now());
            reviewRepo.save(review);
        } catch (ReviewCreationException ex) {
            throw ex;
        } catch (Exception e) {
            throw new ReviewCreationException("Could not create review", e);
        }
    }

    @Override
    public void updateReview(Long id, Review oldReview) {
        Review existing = reviewRepo.findById(id).orElseThrow(() -> new ReviewNotFoundException("Couldn't find review"));
        existing.setRating(oldReview.getRating());
        existing.setText(oldReview.getText());
        reviewRepo.save(existing);
    }

    @Override
    public void deleteReview(Long reviewId) {
            Review foundReview = reviewRepo.findById(reviewId).orElseThrow(() -> (new ReviewNotFoundException("No review found" + reviewId)));
            reviewRepo.delete(foundReview);
    }

    @Override
    public List<ReviewResponseDTO> findAllReviewsForGame(Long gameId) {
        List<Review> reviews = reviewRepo.findAllReviewsForGame(gameId);

        return reviews.stream().map(review -> {
            ProfileResponseDTO response = findProfile(review.getProfileId());
            if(response == null || response.profile() == null) {
                throw new RuntimeException("Couldn't find profile");
            }
            ProfileDTO profile = response.profile();
            ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
            return reviewResponseDTO.toDto(review, profile);
        }).toList();
    }

    @Override
    public List<Review> findAllReviewsForProfile(Long profileId) {
        ProfileResponseDTO response = findProfile(profileId);
        if (response == null|| response.profile() == null) {
            throw new ReviewNotFoundException("Couldn't find profile");
        }
        return reviewRepo.findAllReviewsForProfile(profileId);
    }

    public ProfileResponseDTO findProfile(Long id) {
        return restClient.get()
                .uri(profileServiceUrl + "/getProfile?profileId=" + id)
                .retrieve()
                .body(ProfileResponseDTO.class);
    }

}
