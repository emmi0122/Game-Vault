package se.yrgo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.ReviewLikes;
import se.yrgo.service.reviewLikes.ReviewLikesService;

@RestController
@RequestMapping("likes")
public class ReviewLikesRestController {
    private final ReviewLikesService reviewLikesService;

    public ReviewLikesRestController(ReviewLikesService reviewLikesService) {
        this.reviewLikesService = reviewLikesService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addLike(@RequestParam Long id) {
        reviewLikesService.addLike(id);
        return ResponseEntity.ok("Like added");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteLike(@RequestParam Long reviewId, Long profileId) {
        reviewLikesService.deleteLike(reviewId, profileId);
        return ResponseEntity.ok("Like deleted from review");
    }

}
