package se.yrgo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.dto.LikeRequestDTO;
import se.yrgo.service.reviewLikes.ReviewLikesService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/likes")
public class ReviewLikesRestController {
    private final ReviewLikesService reviewLikesService;

    public ReviewLikesRestController(ReviewLikesService reviewLikesService) {
        this.reviewLikesService = reviewLikesService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addLike(@RequestBody LikeRequestDTO dto) {
        reviewLikesService.addLike(dto.reviewId(), dto.profileId());
        return ResponseEntity.ok(Map.of("message", "Like added"));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteLike(@RequestBody LikeRequestDTO dto) {
        boolean deleted = reviewLikesService.deleteLike(dto.reviewId(), dto.profileId());
        return ResponseEntity.ok(Map.of("message", deleted ? "Like deleted" : "No like found"));
    }

}
