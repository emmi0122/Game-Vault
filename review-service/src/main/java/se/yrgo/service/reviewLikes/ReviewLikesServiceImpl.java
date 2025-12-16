package se.yrgo.service.reviewLikes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import se.yrgo.data.ReviewLikesRepository;
import se.yrgo.data.ReviewRepository;
import se.yrgo.domain.Review;
import se.yrgo.domain.ReviewLikes;
import se.yrgo.dto.ProfileDTO;
import se.yrgo.dto.ProfileResponseDTO;
import se.yrgo.exception.ProfileNotFound;
import se.yrgo.exception.ReviewNotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewLikesServiceImpl implements ReviewLikesService {
    private final ReviewLikesRepository reviewLikesRepository;
    private final ReviewRepository reviewRepository;
    private final RestClient restClient;

    @Value("${profile.service.url}")
    private String profileServiceUrl;


    public ReviewLikesServiceImpl(ReviewLikesRepository rlr, ReviewRepository rr, RestClient restClient) {
        this.reviewLikesRepository = rlr;
        this.reviewRepository = rr;
        this.restClient = restClient;
    }

    public void addLike(Long reviewId, Long profileId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Couldn't find review"));
        ReviewLikes like = new ReviewLikes();
        like.setReview(review);
        like.setLikedAt(Instant.now());
        like.setProfileId(profileId);
        reviewLikesRepository.save(like);
    }

    public boolean deleteLike(Long reviewId, Long profileId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Couldn't find review"));
        Optional<ReviewLikes> likeOpt = review.getLikes().stream()
                .filter(l -> l.getProfileId().equals(profileId))
                .findFirst();
        if(likeOpt.isEmpty()) {
            return false;
        }
        ReviewLikes like = likeOpt.get();
        review.getLikes().remove(like);
        reviewLikesRepository.delete(like);
        return true;
    }

    public ProfileResponseDTO findProfile(Long id) {
        return restClient.get()
                .uri(profileServiceUrl + "/getProfile?profileId=" + id)
                .retrieve()
                .body(ProfileResponseDTO.class);
    }

}
