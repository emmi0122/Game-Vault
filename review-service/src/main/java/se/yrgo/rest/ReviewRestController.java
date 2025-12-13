package se.yrgo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.dto.ReviewResponseDTO;
import se.yrgo.domain.Review;
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
        reviewService.createReview(newReview);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }

    @GetMapping("/all-reviews")
    public List<ReviewResponseDTO> getAllReviewForGame(@RequestParam Long gameId) {
        return reviewService.findAllReviewsForGame(gameId);
    }

    @PostMapping("/delete-review")
    public ResponseEntity<String> removeReview(@RequestParam Long reviewId) {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok("Review deleted");
    }

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
