package se.yrgo.service.review;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import se.yrgo.data.ReviewRepository;
import se.yrgo.domain.Review;
import se.yrgo.dto.ProfileDTO;
import se.yrgo.dto.ProfileResponseDTO;
import se.yrgo.dto.ReviewLikeDTO;
import se.yrgo.dto.ReviewResponseDTO;
import se.yrgo.exception.ProfileNotFound;
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

    /**
     * Creates a review, makes sure that the profile is available in the user-service microservice before creating.
     * Will throw error is nor profile is found.
     * @param review, the review to be created.
     */
    @Override
    public void createReview(Review review) {
        try {
            ProfileResponseDTO response = findProfile(review.getProfileId());
            if (response == null|| response.profile() == null) {
                throw new ProfileNotFound("Couldn't find profile");
            }
            review.setCreatedAt(Instant.now());
            reviewRepo.save(review);
        } catch (ProfileNotFound e) {
            throw e;
        } catch (Exception e) {
            throw new ReviewCreationException("Could not create review", e);
        }
    }

    /**
     * To update a review.
     * Will throw error if no review is found.
     * TODO: Needs to be implemented and looked over once more. Need to make sure this actually work.
     * @param id, the id of the review to be updated
     * @param oldReview, the one to be updated.
     */
    @Override
    public void updateReview(Long id, Review oldReview) {
        Review existing = reviewRepo.findById(id).orElseThrow(() -> new ReviewNotFoundException("Couldn't find review"));
        existing.setRating(oldReview.getRating());
        existing.setText(oldReview.getText());
        reviewRepo.save(existing);
    }

    /**
     * Deletes a review with the reviewId from the database.
     * @param reviewId, the reviewId of the review to be deleted.
     */
    @Override
    public void deleteReview(Long reviewId) {
        Review foundReview = reviewRepo.findById(reviewId).orElseThrow(() -> (new ReviewNotFoundException("No review found" + reviewId)));
        reviewRepo.delete(foundReview);
    }

    /**
     *Find all reviews and their likes for a certain game.
     * Will throw error if no profile is found or response.
     * TODO: Need to implement restClient to check if game exists.
     * @param gameId the game to find all reviews.
     * @return returns all reviews for a certain game.
     */
    @Override
    public List<ReviewResponseDTO> findAllReviewsForGame(Long gameId) {
        List<Review> reviews = reviewRepo.findAllReviewsForGame(gameId);

        return reviews.stream()
                .map(review -> {
            ProfileResponseDTO response = findProfile(review.getProfileId());
            if(response == null || response.profile() == null) {
                throw new ProfileNotFound("Couldn't find profile");
            }

            ProfileDTO profile = response.profile();
            List<ReviewLikeDTO> likeDTO = review.getLikes().stream()
                    .map(like -> {
                        ProfileResponseDTO likeProfileResponse = findProfile(like.getProfileId());
                        String likeUserName = "Unkown";
                        if(likeProfileResponse != null && likeProfileResponse.profile() != null) {
                            likeUserName = likeProfileResponse.profile().getProfileName();
                        }
                        return new ReviewLikeDTO(
                                like.getId(),
                                like.getProfileId(),
                                likeUserName,
                                like.getLikedAt(),
                                review.getId()
                        );
                    }).toList();
            ReviewResponseDTO responseDTO = new ReviewResponseDTO();
            return responseDTO.toDto(review, profile, likeDTO);
        }).toList();
    }

    /**
     * Finds all reviews for a certain profile.
     * Throws error if no profile is found.
     * TODO: Finish this method, right now it's not fully functional.
     * @param profileId, the profile to find.
     * @return All reviews for profile.
     */
    @Override
    public List<Review> findAllReviewsForProfile(Long profileId) {
        ProfileResponseDTO response = findProfile(profileId);
        if (response == null|| response.profile() == null) {
            throw new ProfileNotFound("Couldn't find profile");
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
