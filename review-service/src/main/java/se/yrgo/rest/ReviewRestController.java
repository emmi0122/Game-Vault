package se.yrgo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.dto.ReviewResponseDTO;
import se.yrgo.exception.ReviewCreationException;
import se.yrgo.domain.Review;
import se.yrgo.exception.ReviewNotFoundException;
import se.yrgo.service.review.ReviewService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/reviews")
public class ReviewRestController {
    private final ReviewService reviewService;


    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody Review newReview) {
        try {
            reviewService.createReview(newReview);
            return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
        } catch (ReviewCreationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all-reviews")
    public List<ReviewResponseDTO> getAllReviewForGame(@RequestParam Long gameId) {
        return reviewService.findAllReviewsForGame(gameId);
    }

    @PostMapping("/delete-review")
    public ResponseEntity<String> removeReview(@RequestParam Long reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok("Review deleted");
        } catch(ReviewNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
        }
    }

    // TODO: Should this return the updated review or should the frontend get all reviews again?
    @PostMapping("/update")
    public ResponseEntity<String> updateReview(@RequestParam Long id, @RequestBody Review review) {
        reviewService.updateReview(id, review);
        return ResponseEntity.ok("Review updated");
    }

    @GetMapping("/profile-reviews")
    public List<Review> getAllReviewsForProfile(@RequestParam Long profileId) {
        return reviewService.findAllReviewsForProfile(profileId);
    }
}
