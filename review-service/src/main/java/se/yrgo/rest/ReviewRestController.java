package se.yrgo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import se.yrgo.exception.ReviewCreationException;
import se.yrgo.domain.Review;
import se.yrgo.exception.ReviewNotFoundException;
import se.yrgo.service.review.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewRestController {
    private final ReviewService reviewService;
    private RestClient restClient;

    public ReviewRestController(ReviewService reviewService, RestClient restclient) {
        this.reviewService = reviewService;
        this.restClient = restclient;
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
    public List<Review> getAllReviewForGame(@RequestParam Long gameId) {
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
}
